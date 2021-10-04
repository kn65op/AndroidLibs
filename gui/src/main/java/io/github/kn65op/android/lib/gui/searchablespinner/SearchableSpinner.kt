package io.github.kn65op.android.lib.gui.searchablespinner

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatSpinner

class SearchableSpinner(context: Context, attributeSet: AttributeSet) :
    AppCompatSpinner(context, attributeSet) {

    var onSelectionListener: ((Int) -> Unit)? = null
    var entries: SearchableSpinnerEntries = EmptySearchableSpinnerEntries()
        set(value) {
            field = value
            adapter = entries.adapter()
        }

    init {
        Log.d(LOG_TAG, "Hello")
    }

    override fun setSelection(position: Int) {
        onSelectionListener?.invoke(position)
        super.setSelection(position)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP) {
            Log.d(LOG_TAG, "Touched up - start dialog")
            startDialog()
        }
        return true
    }

    private fun startDialog() {
        SearchableSpinnerDialog(context, entries) { position: Int ->
            setSelection(position)
        }.show()
    }

    companion object {
        const val LOG_TAG = "SearchableSpinner"
    }
}