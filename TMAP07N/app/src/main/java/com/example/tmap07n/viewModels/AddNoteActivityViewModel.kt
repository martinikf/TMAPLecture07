package com.example.tmap07n.viewModels

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tmap07n.MyApplication
import com.example.tmap07n.Room.Note
import kotlinx.coroutines.launch

class AddNoteActivityViewModel (val app: MyApplication) : ViewModel() {

    var AddNoteTitle = ObservableField<String>()
    var  AddNotePrimary = ObservableField<String>()
    var  AddNoteHidden = ObservableField<String>()
    var  AddNoteArchived = ObservableField<Boolean>()


     fun AddNoteClick(view: View){
        viewModelScope.launch {
            app.noteRepository.createNote(
                AddNoteTitle.get().toString(),
                AddNotePrimary.get().toString(),
                AddNoteHidden.get().toString(),
                AddNoteArchived.get() == true
            )
        }
    }
}

class MyModel3Factory(private val app: MyApplication) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddNoteActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddNoteActivityViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}