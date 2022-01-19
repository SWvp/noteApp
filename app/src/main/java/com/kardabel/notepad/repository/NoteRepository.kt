package com.kardabel.notepad.repository

import com.kardabel.notepad.database.NoteDao
import com.kardabel.notepad.model.NoteEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
    ) {
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    fun getAllNotes(): Flow<List<NoteEntity>> = noteDao.getAlphabetizedNotes()

    fun getNoteById(id : Int): Flow<NoteEntity> = noteDao.getNoteById(id)

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    suspend fun insertNote(note: NoteEntity) {
        noteDao.insert(note)
    }

    suspend fun deleteNote(note: NoteEntity) {
        noteDao.delete(note)
    }

    suspend fun deleteNoteById(id: Int) {
        noteDao.deleteNoteById(id)
    }
}