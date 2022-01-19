package com.kardabel.notepad.ui.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kardabel.notepad.ApplicationDispatchers
import com.kardabel.notepad.model.NoteEntity
import com.kardabel.notepad.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateNoteViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val applicationDispatchers: ApplicationDispatchers
) : ViewModel() {

    fun insert(title: String, text: String) {
        val note = NoteEntity(
            title,
            text
        )
        insertNote(note)
     }

    private fun insertNote(note: NoteEntity) =
        viewModelScope.launch(applicationDispatchers.ioDispatcher) {
            noteRepository.insertNote(note)
        }
}