package com.kardabel.notepad.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.ViewModelProvider
import com.kardabel.notepad.ui.notes.MainActivity



@AndroidEntryPoint
class ConfirmDeleteNoteFragment : DialogFragment() {

    interface ConfirmDeleteListener {
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    companion object {
        fun newInstance(id: Int) = ConfirmDeleteNoteFragment().apply {
            arguments = Bundle().apply {
                putInt("noteId", id)
            }
        }
    }

    var listener: ConfirmDeleteListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val noteId = arguments?.get("noteId")
        val confirmDialogViewModel = ViewModelProvider(this)[ConfirmDeleteFragmentViewModel::class.java]
        builder
            .setMessage("Delete note ?")
            .setPositiveButton("Delete", DialogInterface.OnClickListener { dialog, id ->
                confirmDialogViewModel.deleteNote(noteId as Int)
                //listener?.onDialogPositiveClick()
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                listener?.onDialogNegativeClick()
            })

        return builder.create()

    }
}