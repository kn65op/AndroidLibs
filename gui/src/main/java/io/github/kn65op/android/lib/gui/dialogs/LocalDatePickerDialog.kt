package io.github.kn65op.android.lib.gui.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import java.time.LocalDate

class LocalDatePickerDialog @RequiresApi(Build.VERSION_CODES.O) constructor(
    private val listener: DatePickerListener,
    private val date: LocalDate = LocalDate.now()
) :
    DialogFragment(), DatePickerDialog.OnDateSetListener {

    interface DatePickerListener {
        fun onDateSet(dialog: DialogFragment, localDate: LocalDate)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        Log.i(LOG_TAG, "Create date from: $date")

        return DatePickerDialog(
            requireContext(),
            this,
            date.year,
            date.monthValue - 1,
            date.dayOfMonth
        )
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        listener.onDateSet(this, LocalDate.of(year, month + 1, day))
    }

    companion object {
        private const val LOG_TAG = "DataPickerFragment"
    }
}
