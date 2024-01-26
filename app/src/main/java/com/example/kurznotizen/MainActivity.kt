package com.example.kurznotizen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.kurznotizen.database.MyRepository
import com.example.kurznotizen.database.NoteDatabase
import com.example.kurznotizen.databinding.ActivityMainBinding
import com.example.kurznotizen.viewmodel.NotesViewModel
import com.example.kurznotizen.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    lateinit var notesViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        setUpViewModel()
    }

    private fun setUpViewModel() {


        val noteRepository = MyRepository(NoteDatabase.invoke(this))

        val viewModelProviderFactory = ViewModelFactory(application, noteRepository)

        notesViewModel = ViewModelProvider(
            this,
            viewModelProviderFactory)
            .get(NotesViewModel::class.java)

    }
}








