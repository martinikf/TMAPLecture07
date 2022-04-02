package com.example.tmap07n

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.tmap07n.databinding.ActivityAddNoteBinding
import com.example.tmap07n.databinding.ActivityNoteDetailBinding
import com.example.tmap07n.viewModels.AddNoteActivityViewModel
import com.example.tmap07n.viewModels.MyModel2Factory
import com.example.tmap07n.viewModels.MyModel3Factory
import com.example.tmap07n.viewModels.NoteDetailActivityViewModel

class AddNoteActivity : AppCompatActivity() {
    private val model: AddNoteActivityViewModel by viewModels { MyModel3Factory((application as MyApplication)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        //Binding
        val binding: ActivityAddNoteBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_add_note)
        binding.model = model

    }
}