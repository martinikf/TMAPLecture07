package com.example.tmap07n.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(@PrimaryKey(autoGenerate = true) val id:Long = 0,
                @ColumnInfo(name="note_title") var title: String,
                var primaryText : String,
                var hiddenText: String,
                var archived: Boolean) {

    constructor(title: String, text: String, hiddenText: String, archived: Boolean) : this(0, title, text, hiddenText, archived)
}