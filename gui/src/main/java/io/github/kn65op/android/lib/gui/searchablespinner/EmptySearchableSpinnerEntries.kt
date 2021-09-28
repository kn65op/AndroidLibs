package io.github.kn65op.android.lib.gui.searchablespinner

import android.widget.SpinnerAdapter

class EmptySearchableSpinnerEntries : SearchableSpinnerEntries {
    override fun texts() = emptyList<String>()
    override fun adapter(): SpinnerAdapter? = null
}