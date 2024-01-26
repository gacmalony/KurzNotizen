package com.example.kurznotizen.database

import com.example.kurznotizen.model.Notes

class MyRepository(private val dao: NoteDatabase) {


    suspend fun insert(notes: Notes)=dao.getNoteDao().insertNote(notes)

    suspend fun delete(notes: Notes)=dao.getNoteDao().deleteNote(notes)

    suspend fun update(notes:Notes)=dao.getNoteDao().updateNote(notes)

    fun getAllNotes() = dao.getNoteDao().getAllNotes()
    fun searchNotes(query:String?) = dao.getNoteDao().searchNote(query)


}