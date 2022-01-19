package com.kardabel.notepad.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kardabel.notepad.ApplicationDispatchers
import com.kardabel.notepad.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfirmDeleteFragmentViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val applicationDispatchers: ApplicationDispatchers

) : ViewModel() {

    fun deleteNote(id: Int) =
        viewModelScope.launch(applicationDispatchers.ioDispatcher){
            noteRepository.deleteNoteById(id)
        }
}