package com.example.quakeapplication.ui.quakes

import androidx.appcompat.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.SeekBar
import androidx.fragment.app.DialogFragment
import com.example.quakeapplication.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.slider.Slider
import java.lang.ClassCastException

class FilterDialogFragment : DialogFragment() {

    private lateinit var listener: FilterDialogListener

    interface FilterDialogListener {
        fun onDialogPositiveClick(seekBarProgress: Int)
        fun onDialogNegativeClick()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = parentFragment as FilterDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$parentFragment must implement FilterDialogListener")
        }

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = MaterialAlertDialogBuilder(it)

            builder.setTitle(R.string.filter_dialog_title)
                .setView(requireActivity().layoutInflater.inflate(R.layout.fragment_filter_dialog, null))
                .setPositiveButton(R.string.filter_dialog_positive_button_text) { dialog, id ->
                        // Send the positive button event back to the host fragment
                        val seekBar = (dialog as AlertDialog).findViewById<Slider>(R.id.filter_seekBar)
                        listener.onDialogPositiveClick(seekBar!!.value.toInt())
                    }
                .setNegativeButton(R.string.filter_dialog_negative_button_text) { dialog, id ->
                    // Send the negative button event back to the host fragment
                    listener.onDialogNegativeClick()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}