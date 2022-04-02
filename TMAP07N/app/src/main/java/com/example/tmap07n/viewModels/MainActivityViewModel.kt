package com.example.tmap07n.viewModels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tmap07n.MyApplication
import kotlinx.coroutines.launch

class MainActivityViewModel(val app: MyApplication) : ViewModel() {
    val notes = app.noteRepository.getAllNotes()

    var noteAddTitle = ObservableField<String>()
    var noteAddBody = ObservableField<String>()

    fun insert(title: String, primary: String, hidden: String, archived: Boolean) {
        viewModelScope.launch {
            app.noteRepository.createNote(title, primary, hidden, archived)

        }
    }
}

class MyModelFactory(private val app: MyApplication) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainActivityViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}