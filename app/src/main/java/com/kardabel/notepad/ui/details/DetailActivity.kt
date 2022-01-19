package com.kardabel.notepad.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.kardabel.notepad.R
import com.kardabel.notepad.databinding.ActivityDetailBinding
import com.kardabel.notepad.ui.ConfirmDeleteNoteFragment
import com.kardabel.notepad.ui.notes.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var confirmDialogFragment: ConfirmDeleteNoteFragment
    private lateinit var binding: ActivityDetailBinding
    private val detailsViewModel: DetailsActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val id = intent.getIntExtra("id", -1)
        confirmDialogFragment = ConfirmDeleteNoteFragment.newInstance(id)


        detailsViewModel.init(id)

        detailsViewModel.newNoteLiveData.observe(this) { note ->
            binding.detailTitle.setText(note.title)
            binding.detailText.setText(note.text)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                saveNote()
                true
            }
            R.id.action_delete -> {
                showDeleteMessage()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showDeleteMessage() {
       // val id = intent.getIntExtra("id", -1)
        val confirmFragment = ConfirmDeleteNoteFragment()
      confirmFragment.listener = object : ConfirmDeleteNoteFragment.ConfirmDeleteListener {
          override fun onDialogPositiveClick() {
              //deleteNote()

              finish()

          }

          override fun onDialogNegativeClick() {}

      }
        confirmDialogFragment.show(supportFragmentManager, "confirmDeleteMessage")
    }

    private fun saveNote() {
        finish()
    }

 //  private fun deleteNote() {
 //      detailsViewModel.delete(thisNote)

 //  }
}