package com.kardabel.notepad.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kardabel.notepad.ApplicationDispatchers
import com.kardabel.notepad.model.NoteEntity
import com.kardabel.notepad.model.NoteViewState
import com.kardabel.notepad.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val applicationDispatchers: ApplicationDispatchers
) : ViewModel() {

    val allNotes: LiveData<List<NoteViewState>> = noteRepository.getAllNotes().map { notes ->
        notes.map {
            NoteViewState(
                it.title,
                it.text,
                it.id
            )
        }
    }.asLiveData(applicationDispatchers.ioDispatcher)

    // Launching a new coroutine to insert the data in a non-blocking way
    fun insert(note: NoteEntity) = viewModelScope.launch(applicationDispatchers.ioDispatcher) {
        noteRepository.insertNote(note)
    }
}