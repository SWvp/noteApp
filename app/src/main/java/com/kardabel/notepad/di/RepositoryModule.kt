package com.kardabel.notepad.di

import com.kardabel.notepad.database.NoteDao
import com.kardabel.notepad.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideNoteRepository(
        noteDao: NoteDao
    ): NoteRepository{
        return NoteRepository(noteDao)
    }
}