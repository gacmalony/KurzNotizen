package com.example.kurznotizen.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kurznotizen.MainActivity
import com.example.kurznotizen.R
import com.example.kurznotizen.adapter.NoteAdapter
import com.example.kurznotizen.databinding.FragmentHomeBinding
import com.example.kurznotizen.databinding.FragmentUpdateNoteBinding
import com.example.kurznotizen.model.Notes
import com.example.kurznotizen.viewmodel.NotesViewModel


class UpdateNoteFragment : Fragment() {

    private var binder: FragmentUpdateNoteBinding? = null

    private val binding get()= binder!!

    private lateinit var notesViewModel: NotesViewModel

    private lateinit var noteAdapter: NoteAdapter

    private lateinit var currentNote : Notes

    private val args: UpdateNoteFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binder = FragmentUpdateNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel = (activity as MainActivity).notesViewModel
        currentNote  = args.note!!


        binding.titleupdatenote.setText(currentNote.title)
        binding.bodyupdatenote.setText(currentNote.body)

        binding.floatingupdatenote.setOnClickListener(){
            val title = binding.titleupdatenote.text.toString().trim()
            val body = binding.bodyupdatenote.text.toString().trim()

            if (title.isNotEmpty()){
                val note = Notes(currentNote.id,title, body)
                notesViewModel.updateNote(note)
                view.findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }else{
                Toast.makeText(view.context, "Please Enter note Title", Toast.LENGTH_LONG).show()
            }
        }


        }


    private fun deleteNote(){
        AlertDialog.Builder(activity).apply{
            setTitle("Delete Note")
            setMessage("Are you Sure you want to delete this note!!")
            setPositiveButton("Delete"){_,_ ->
                notesViewModel.deleteNote(currentNote)

                view?.findNavController()?.navigate(
                    R.id.action_updateNoteFragment_to_homeFragment
                )

            }
            setNegativeButton("Cancel", null)
        }.create().show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_update, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_delete -> {
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        binder = null
    }



}