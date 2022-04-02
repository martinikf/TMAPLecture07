package com.example.tmap07n

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.tmap07n.databinding.ActivityMainBinding
import com.example.tmap07n.databinding.ActivityNoteDetailBinding
import com.example.tmap07n.viewModels.MainActivityViewModel
import com.example.tmap07n.viewModels.MyModel2Factory
import com.example.tmap07n.viewModels.MyModelFactory
import com.example.tmap07n.viewModels.NoteDetailActivityViewModel

//TODO TITLE
class NoteDetailActivity : AppCompatActivity() {
    private val model: NoteDetailActivityViewModel by viewModels { MyModel2Factory((application as MyApplication)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        //Binding
        val binding: ActivityNoteDetailBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_note_detail)
        binding.model = model

        //Get title of clicked note
        val title = intent.getStringExtra("NoteTitle")

        //Set ui to clicked note data
        model.setFieldsByTitle(title)
    }


}