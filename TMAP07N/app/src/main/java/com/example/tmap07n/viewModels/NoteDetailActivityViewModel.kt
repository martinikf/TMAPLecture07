package com.example.tmap07n.viewModels

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.tmap07n.MyApplication
import com.example.tmap07n.Room.Note
import kotlinx.coroutines.launch

class NoteDetailActivityViewModel (val app: MyApplication) : ViewModel() {

    var detailTitle = ObservableField<String>()
    var detailPrimary = ObservableField<String>()
    var detailHidden = ObservableField<String>()
    var detailArchived = ObservableField<Boolean>()

    var originalNote: Note? = null

    fun setFieldsByTitle(title: String?){
        Log.w("Info", "setFieldsByTitle")
        if(title == null) return;

        val foundNote: Note? = app.noteRepository.getNoteByTitle(title)
        originalNote = foundNote

        detailTitle.set(foundNote?.title)
        detailPrimary.set(foundNote?.primaryText)
        detailHidden.set(foundNote?.hiddenText)
        detailArchived.set(foundNote?.archived)
    }

    fun saveClick(view: View){
        if(originalNote != null){
            if(originalNote!!.title != detailTitle.get().toString()){
                if(app.noteRepository.getNoteByTitle(detailTitle.get().toString()) != null){
                    Log.w("Warn","Title is not unique")
                    return
                }
                else
                    originalNote!!.title = detailTitle.get().toString()
            }
            originalNote!!.primaryText = detailPrimary.get().toString()
            originalNote!!.hiddenText = detailHidden.get().toString()
            originalNote!!.archived = detailArchived.get() == true

            app.noteRepository.updateNote(originalNote!!)
        }
    }
}

class MyModel2Factory(private val app: MyApplication) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteDetailActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteDetailActivityViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}