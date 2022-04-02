package com.example.tmap07n

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.tmap07n.databinding.ActivityAddNoteBinding
import com.example.tmap07n.databinding.ActivitySettingsBinding
import com.example.tmap07n.viewModels.AddNoteActivityViewModel
import com.example.tmap07n.viewModels.MyModel3Factory
import com.example.tmap07n.viewModels.MyModel4Factory
import com.example.tmap07n.viewModels.SettingsActivityViewModel

class SettingsActivity : AppCompatActivity() {
    private val model: SettingsActivityViewModel by viewModels { MyModel4Factory((application as MyApplication)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val binding: ActivitySettingsBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_settings)
        binding.model = model

        SetSwitchToCorrectState()
    }

    fun SetSwitchToCorrectState(){
        val sharedPref = getSharedPreferences("PREF", Context.MODE_PRIVATE) ?: return
        val checked = sharedPref.getBoolean(getString(R.string.Archive), false);
        model.SwitchChecked.set(checked)
    }

    fun SwitchArchiveClick(view: View) {
        model.SwitchArchiveClick(view)
        val a = model.SwitchChecked.get()

        //Uloží a do SharedPreferences
        val sharedPref = getSharedPreferences("PREF", Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putBoolean(getString(R.string.Archive), a!!)
            apply()
        }
    }
}