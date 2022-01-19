package com.kardabel.notepad

import android.app.Application
import com.kardabel.notepad.database.NoteRoomDatabase
import com.kardabel.notepad.repository.NoteRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NoteApplication : Application()