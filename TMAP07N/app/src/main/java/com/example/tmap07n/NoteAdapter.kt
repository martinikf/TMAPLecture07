package com.example.tmap07n

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tmap07n.Room.Note

class NoteAdapter(private var notes: MutableList<Note>) : RecyclerView.Adapter<NoteAdapter.NoteItemHolder> () {

    inner class NoteItemHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        internal var title = view.findViewById<TextView>(R.id.itemTitle)
        internal var body = view.findViewById<TextView>(R.id.itemPrimaryText)

        init {
            view.isClickable = true
            view.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            //note clicked
            //mainActivity.displayDisplay(title.text.toString())
            Log.w("Info", "Note clicked" + title)

            //TODO TITLE | Access notes by unique IDs, now they are distinguishable only by their titles
            val intent = Intent(view?.context, NoteDetailActivity :: class.java)
            intent.putExtra("NoteTitle", title.text.toString())
            view?.context?.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return NoteItemHolder(item)
    }

    override fun onBindViewHolder(holder: NoteItemHolder, pos: Int) {

        val note = notes[pos]
        if(note.archived) {
            holder.title.setTextColor(Color.GREEN)
            holder.body.setTextColor(Color.GREEN)
        }
        else{
            holder.title.setTextColor(Color.GRAY)
            holder.body.setTextColor(Color.GRAY)
        }
        holder.title.text = note.title
        holder.body.text = note.primaryText
    }

    override fun getItemCount(): Int = notes.size

    fun setNotesAll(notes: List<Note>) {

        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }

    fun setNotesUnarchived(notes: List<Note>) {

        this.notes.clear()
        this.notes.addAll(notes.filter { x-> !x.archived })

        notifyDataSetChanged()
    }
}