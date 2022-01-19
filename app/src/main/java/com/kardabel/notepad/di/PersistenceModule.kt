package com.kardabel.notepad.di

import android.content.Context
import androidx.room.Room
import com.kardabel.notepad.database.NoteDao
import com.kardabel.notepad.database.NoteRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): NoteRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            NoteRoomDatabase::class.java,
            "note_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNotes(database: NoteRoomDatabase): NoteDao{
        return database.noteDao()
    }

}