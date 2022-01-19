package com.kardabel.notepad.ui.details

import androidx.lifecycle.*
import com.kardabel.notepad.ApplicationDispatchers
import com.kardabel.notepad.model.NoteEntity
import com.kardabel.notepad.model.NoteViewState
import com.kardabel.notepad.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsActivityViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val applicationDispatchers: ApplicationDispatchers
) : ViewModel() {

    lateinit var newNoteLiveData: LiveData<NoteViewState>

    fun init(id: Int) {
        newNoteLiveData = Transformations.map(
            noteRepository
                .getNoteById(id)
                .asLiveData(applicationDispatchers.ioDispatcher),
            fun(it: NoteEntity): NoteViewState {
                    return mapNote(it)
            }
        )
    }

    fun delete(note: NoteEntity) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

    private fun mapNote(note: NoteEntity) = NoteViewState(
        note.title,
        note.text,
        note.id
    )
}