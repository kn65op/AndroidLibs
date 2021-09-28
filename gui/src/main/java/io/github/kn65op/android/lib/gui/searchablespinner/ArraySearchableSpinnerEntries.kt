package io.github.kn65op.android.lib.gui.searchablespinner

import android.content.Context
import android.widget.ArrayAdapter

class ArraySearchableSpinnerEntries<T>(
    val entries: Array<T>,
    val converter: SearchableSpinnerEntryConverter<T>,
    val context: Context,
) :
    SearchableSpinnerEntries {
    override fun texts() = entries.map { converter.toText(it) }

    override fun adapter() : ArrayAdapter<String> {
        val adapter = ArrayAdapter<String>(context, android.R.layout.simple_spinner_item)
        adapter.addAll(texts())
        return adapter
    }
}