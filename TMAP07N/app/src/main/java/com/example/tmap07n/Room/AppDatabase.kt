package com.example.tmap07n.Room

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room

import androidx.room.RoomDatabase


@Database(entities = [Note::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase {
            if (instance != null) {
                return instance as AppDatabase
            } else {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "MojeDatabase"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                return instance as AppDatabase
            }
        }
    }
}