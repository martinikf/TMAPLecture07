package com.example.tmap07n.viewModels

import android.content.Context
import android.view.View
import android.widget.Switch
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tmap07n.MyApplication
import com.example.tmap07n.R
import java.util.*

class SettingsActivityViewModel (val app: MyApplication) : ViewModel() {

    val SwitchChecked = ObservableField<Boolean>()

    fun SwitchArchiveClick(view: View){
        return
    }
}

class MyModel4Factory(private val app: MyApplication) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingsActivityViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}