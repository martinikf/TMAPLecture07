package com.example.tmap07n

import android.app.Application
import com.example.tmap07n.Repositories.NoteRepository
import com.example.tmap07n.Room.AppDatabase

class MyApplication : Application(){
    val database by lazy { AppDatabase.getDatabase(this)}
    val noteRepository by lazy { NoteRepository(database) }


}