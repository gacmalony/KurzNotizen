package com.example.kurznotizen.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.kurznotizen.MainActivity
import com.example.kurznotizen.R
import com.example.kurznotizen.adapter.NoteAdapter
import com.example.kurznotizen.databinding.FragmentHomeBinding
import com.example.kurznotizen.databinding.FragmentNewNoteBinding
import com.example.kurznotizen.model.Notes
import com.example.kurznotizen.viewmodel.NotesViewModel


class NewNoteFragment : Fragment(R.layout.fragment_new_note) {


    private var binder: FragmentNewNoteBinding? = null

    private val binding get()= binder!!

    private lateinit var notesViewModel: NotesViewModel

    private lateinit var noteAdapter: NoteAdapter

    private lateinit var mView: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binder = FragmentNewNoteBinding.inflate(inflater, container, false)

        return binder!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel = (activity as MainActivity).notesViewModel
        mView = view

        binding.menuSave.setOnClickListener(){
            saveNote(mView)
        }
    }


    fun saveNote(view: View){
        val noteTitle = binding.titlenewnote.text.toString()
        val noteBody = binding.bodynewnote.text.toString()

        if(noteTitle.isNotEmpty()){
            val note = Notes(0,noteTitle,noteBody)

            notesViewModel.addNote(note)

            Toast.makeText(mView.context, "Note Saved Succesfully!", Toast.LENGTH_SHORT).show()

            view.findNavController().navigate(R.id.action_newNoteFragment_to_homeFragment)

        }else{
            Toast.makeText(mView.context, "Please Enter Note Title",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.new_note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)


    }


    override fun onDestroy() {
        super.onDestroy()
        binder = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_save -> {
                saveNote(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    }