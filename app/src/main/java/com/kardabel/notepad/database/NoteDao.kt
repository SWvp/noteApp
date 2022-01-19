package com.kardabel.notepad.database

import androidx.room.*
import com.kardabel.notepad.model.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note ORDER BY title ASC")
    fun getAlphabetizedNotes(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note WHERE id=:id ORDER BY title ASC")
    fun getNoteById(id : Int): Flow<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)

    @Query("DELETE FROM note WHERE id = :id")
    suspend fun deleteNoteById(id: Int)
}