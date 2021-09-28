package io.github.kn65op.android.lib.gui.searchablespinner

import android.widget.SpinnerAdapter

interface SearchableSpinnerEntries {
    fun texts(): List<String>
    fun adapter(): SpinnerAdapter?
}
