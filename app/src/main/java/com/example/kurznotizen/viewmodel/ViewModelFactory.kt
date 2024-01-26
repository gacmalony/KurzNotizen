package com.example.kurznotizen.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kurznotizen.database.MyRepository
import com.example.kurznotizen.model.Notes

class ViewModelFactory(val app: Application, private val repository: MyRepository):

    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(NotesViewModel::class.java)){
            return NotesViewModel(app, repository) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}