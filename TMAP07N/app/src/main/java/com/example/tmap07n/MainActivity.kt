package com.example.tmap07n

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmap07n.Room.Note
import com.example.tmap07n.viewModels.MainActivityViewModel
import com.example.tmap07n.viewModels.MyModelFactory
import com.example.tmap07n.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val model: MainActivityViewModel by viewModels { MyModelFactory((application as MyApplication)) }

    private var recyclerView: RecyclerView? = null
    private var adapter: NoteAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)

        binding.model = model

        adapter = NoteAdapter(mutableListOf<Note>())
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView = findViewById<RecyclerView>(R.id.recView)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        recyclerView?.adapter = adapter
        recyclerView?.adapter?.notifyDataSetChanged()

        refreshRecycler()

    }

    fun refreshRecycler(){
        val sharedPref = getSharedPreferences("PREF", Context.MODE_PRIVATE) ?: return
        val checked = sharedPref.getBoolean(getString(R.string.Archive), false);


        model.notes.observe(this) {
            if(checked)
                adapter?.setNotesAll(it)
            else
                adapter?.setNotesUnarchived(it)

            recyclerView?.scrollToPosition( it .size - 1 )}
    }

    fun fabNewNoteClick(view: View){
        Log.w("Info", "New note clicked")

        val intent = Intent(this, AddNoteActivity :: class.java)
        startActivity( intent)
    }

    fun fabSettingsClick(view: View) {
        Log.w("Info", "Settings clicked")
        val intent = Intent(this, SettingsActivity :: class.java)
        startActivityForResult( intent, 1)
    }

    fun fabWipeClick(view: View) {
        Log.w("Info", "Wipe clicked")
        model.app.noteRepository.wipe()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1) {
            refreshRecycler()
        }
    }
}