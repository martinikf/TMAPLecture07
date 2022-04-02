package com.example.tmap07n.Room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    fun getAll () : LiveData<List<Note>>
    @Query("SELECT * FROM notes WHERE note_title LIKE :title LIMIT 1")
    fun findByTitle ( title : String): Note?
    @Insert
    fun insert (note: Note): Long
    @Delete
    fun delete(note: Note)
    @Query("DELETE FROM notes")
    fun deleteAll()
    @Update
    fun update(notes: List<Note>) : Int
    @Update
    fun update(note: Note)

}