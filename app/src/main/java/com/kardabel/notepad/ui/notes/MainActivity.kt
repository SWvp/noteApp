package com.kardabel.notepad.ui.notes

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kardabel.notepad.databinding.ActivityMainBinding
import com.kardabel.notepad.model.NoteViewState
import com.kardabel.notepad.ui.NoteAdapter
import com.kardabel.notepad.ui.create.CreateNote
import com.kardabel.notepad.ui.details.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var noteAdapter: NoteAdapter
    private lateinit var binding: ActivityMainBinding
    private val noteViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)

        noteAdapter = NoteAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = noteAdapter

        noteViewModel.allNotes.observe(this) { notes ->
            notes.let { noteAdapter.submitList(it) }
        }

        noteAdapter.setOnItemClickedListener(
            object : NoteAdapter.OnItemClickListener {
                override fun onItemClicked(note: NoteViewState) {
                    showNoteDetail(note)
                }
            }
        )

        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateNote::class.java)
            startActivity(intent)
        }
    }

    private fun showNoteDetail(note: NoteViewState) {

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("id", note.id)
        startActivity(intent)
    }
}