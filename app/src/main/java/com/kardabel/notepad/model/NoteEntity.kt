package com.kardabel.notepad.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteEntity(
    @ColumnInfo (name = "title") var title: String = "",
    @ColumnInfo (name = "text") var text: String = "",
    @PrimaryKey(autoGenerate = true) val id: Int = 0)  {

}