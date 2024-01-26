package com.example.kurznotizen.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kurznotizen.databinding.NotesBinding
import com.example.kurznotizen.fragments.HomeFragment
import com.example.kurznotizen.fragments.HomeFragmentDirections
import com.example.kurznotizen.model.Notes
import java.util.*

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.MyViewHolder>() {



    class MyViewHolder(val itembinding:NotesBinding) : RecyclerView.ViewHolder(itembinding.root)

        private val differcallback = object :DiffUtil.ItemCallback<Notes>(){
            override fun areItemsTheSame(oldItem: Notes, newItem: Notes): Boolean {
                return oldItem.id == newItem.id &&
                        oldItem.body == newItem.body &&
                        oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Notes, newItem: Notes): Boolean {
                return oldItem == newItem
            }

        }

        val differ =  AsyncListDiffer(this,differcallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(NotesBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currrentNote = differ.currentList[position]
        holder.itembinding.notestitle.text = currrentNote.title
        holder.itembinding.notesbody.text = currrentNote.body

        val random = Random()
        val color = Color.argb(255, random.nextInt(256), random.nextInt(256),random.nextInt(256))

        holder.itembinding.notesview.setBackgroundColor(color)

        holder.itemView.setOnClickListener(){
            val direction = HomeFragmentDirections.actionHomeFragmentToUpdateNoteFragment(currrentNote)
            it.findNavController().navigate(direction)
        }



    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }



}