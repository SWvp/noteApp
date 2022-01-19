package com.kardabel.notepad.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kardabel.notepad.model.NoteEntity

@Database(entities = arrayOf(NoteEntity::class), version = 1, exportSchema = false)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}