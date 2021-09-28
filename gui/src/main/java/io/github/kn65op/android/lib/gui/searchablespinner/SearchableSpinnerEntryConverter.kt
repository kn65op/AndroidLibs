package io.github.kn65op.android.lib.gui.searchablespinner

interface SearchableSpinnerEntryConverter<T> {
    fun toText(t: T): String
}
