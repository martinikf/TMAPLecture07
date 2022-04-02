package com.example.tmap07n.Repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.tmap07n.Room.AppDatabase
import com.example.tmap07n.Room.Note


class NoteRepository(val database: AppDatabase) {

    suspend fun createNote(title:String, text:String, hidden:String, archived: Boolean) : Note? {
        if(getNoteByTitle(title) == null) {
            val note = Note(title, text, hidden, archived)
            val newId = database.noteDao().insert(note)
            return note.copy(id = newId)
        }
        Log.w("Warn","Title is not unique")
        return null
    }

    fun getAllNotes() : LiveData<List<Note>> = database.noteDao().getAll()

    fun getNoteByTitle(title: String) = database.noteDao().findByTitle(title)

    fun updateNote(note: Note){
       database.noteDao().update(note)
    }

    fun wipe(){
        database.noteDao().deleteAll()
    }

    fun deleteNote(note:Note) {
        // check oprvánění
        database.noteDao().delete(note)
    }
}