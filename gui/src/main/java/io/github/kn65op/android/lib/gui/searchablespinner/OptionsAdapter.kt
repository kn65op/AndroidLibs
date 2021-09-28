package io.github.kn65op.android.lib.gui.searchablespinner

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.kn65op.android.lib.gui.R

class OptionsAdapter(
    private val originalEntries: SearchableSpinnerEntries,
    val callback: (Int) -> Unit
) :
    RecyclerView.Adapter<OptionsAdapter.ViewHolder>() {
    var entries = originalEntries.texts().withIndex()

    class ViewHolder(
        val view: View,
        val callback: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.searchable_selection_option_row_item_text)

        fun setListener(position: Int) {
            view.setOnClickListener {
                Log.i(LOG_TAG, "Selected: $position")
                callback(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.searchable_selection_option_row_item, parent, false)
        return ViewHolder(view, callback)
    }

    override fun getItemCount(): Int {
        Log.i(LOG_TAG, "Items: ${entries.count()}")
        return entries.count()
    }

    override fun onBindViewHolder(holder: OptionsAdapter.ViewHolder, position: Int) {
        val element = entries.elementAt(position)
        holder.setListener(element.index)
        holder.textView.text = element.value
    }

    fun filter(text: String) {
        val regexText = ".*$text.*".toRegex(RegexOption.IGNORE_CASE)
        entries = originalEntries.texts().withIndex().filter { it.value.matches(regexText) }
        Log.i(LOG_TAG, "Items: ${entries.count()}")
    }

    companion object {
        const val LOG_TAG = "OptionsAdapter"
    }
}