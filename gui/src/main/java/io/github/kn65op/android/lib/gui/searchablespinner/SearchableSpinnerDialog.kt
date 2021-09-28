package io.github.kn65op.android.lib.gui.searchablespinner

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import io.github.kn65op.android.lib.gui.R

class SearchableSpinnerDialog(
    val context: Context, entries: SearchableSpinnerEntries,
    val callback: (Int) -> Unit
) {
    val originalEntries = entries
    lateinit var viewForOptions: RecyclerView
    lateinit var searchField: TextInputEditText
    lateinit var optionsAdapter: OptionsAdapter
    lateinit var dialog: Dialog

    fun show() {
        val builder = AlertDialog.Builder(context)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.searchable_selection_dialog, null)

        searchField = view.findViewById(R.id.searchable_selection_search_field)
        searchField.doOnTextChanged { text, start, before, count ->
            optionsAdapter.filter(text.toString())
            viewForOptions.adapter?.notifyDataSetChanged()
        }

        viewForOptions = view.findViewById(R.id.searchable_selection_view_for_options)
        viewForOptions.layoutManager = LinearLayoutManager(context)
        optionsAdapter = OptionsAdapter(originalEntries, { position: Int -> exitDialog(position) })
        viewForOptions.adapter = optionsAdapter

        builder.setView(view)
        dialog = builder.create()
        dialog.show()
    }

    fun exitDialog(position: Int) {
        callback(position)
        dialog.hide()
    }

    companion object {
        const val LOG_TAG = "SearchableSpinnerDialog"
    }
}