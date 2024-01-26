package com.example.kurznotizen.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kurznotizen.database.MyRepository
import com.example.kurznotizen.model.Notes
import kotlinx.coroutines.launch

open class NotesViewModel(app: Application, private val repository: MyRepository): AndroidViewModel(app) {


    fun addNote(notes: Notes) = viewModelScope.launch {

        repository.insert(notes)
    }

    fun deleteNote(notes:Notes) = viewModelScope.launch {

        repository.delete(notes)
    }

    fun updateNote(notes:Notes) = viewModelScope.launch {

        repository.update(notes)
    }

    fun getAllNotes() = repository.getAllNotes()
    fun searchNotes(query:String?) = repository.searchNotes(query)



}

