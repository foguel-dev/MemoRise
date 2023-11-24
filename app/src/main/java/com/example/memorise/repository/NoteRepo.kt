package com.example.memorise.repository

import com.example.memorise.data.database.NoteDatabase
import com.example.memorise.model.Note

class NoteRepo(private val database: NoteDatabase) {

    suspend fun insertNote(note: Note) = database.getDao().insert(note)
    suspend fun deleteNote(note: Note) = database.getDao().delete(note)
    fun getNotes() = database.getDao().getNotes()

}