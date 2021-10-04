package io.github.kn65op.android.lib.gui.searchablespinner

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatSpinner

class SearchableSpinner(context: Context, attributeSet: AttributeSet) :
    AppCompatSpinner(context, attributeSet) {

    var onSelectionListener: ((Int) -> Unit)? = null
        set(value) {
            field = value
            val initialPosition = 0
            setSelection(initialPosition)
        }

    var entries: SearchableSpinnerEntries = EmptySearchableSpinnerEntries()
        set(value) {
            field = value
            adapter = entries.adapter()
        }

    init {
        Log.d(LOG_TAG, "Hello")
    }

    override fun setSelection(position: Int) {
        val minPosition = 0
        val maxPosition = entries.texts().size
        if (position in minPosition until maxPosition) {
            onSelectionListener?.invoke(position)
            super.setSelection(position)
        } else {
            Log.i(LOG_TAG, "Selected invalid position, $position not in [$minPosition; $maxPosition)")
        }
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