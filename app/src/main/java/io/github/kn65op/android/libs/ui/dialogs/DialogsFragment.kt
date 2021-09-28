package io.github.kn65op.android.libs.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import io.github.kn65op.android.lib.gui.searchablespinner.SearchableSpinner
import io.github.kn65op.android.lib.gui.searchablespinner.ArraySearchableSpinnerEntries
import io.github.kn65op.android.lib.gui.searchablespinner.SearchableSpinnerEntryConverter
import io.github.kn65op.android.libs.databinding.FragmentDialogsBinding

class DialogsFragment : Fragment() {

    private var _binding: FragmentDialogsBinding? = null
    private lateinit var searchableSpinner: SearchableSpinner
    private lateinit var searchableSpinnerResult: TextView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogsBinding.inflate(inflater, container, false)
        searchableSpinner = binding.searchableSpinner
        val spinnerOptions =
            dataForSpinner.withIndex().map { "${it.index}: ${it.value}" }.toTypedArray()
        searchableSpinner.entries = ArraySearchableSpinnerEntries(
            spinnerOptions,
            object : SearchableSpinnerEntryConverter<String> {
                override fun toText(t: String) = t
            },
            requireContext()
        )
        searchableSpinner.onSelectionListener = { position: Int ->
            binding.searchableSpinnerSelectedValue.text = dataForSpinner[position]
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val LOG_TAG = "DIALOGS"
    }
}