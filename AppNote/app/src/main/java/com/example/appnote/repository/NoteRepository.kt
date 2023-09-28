package com.example.appnote.repository

import com.example.appnote.data.NoteDatabaseDao
import com.example.appnote.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {
    suspend fun addNote(note: Note) = noteDatabaseDao.addNote(note = note)
    suspend fun updateNote(note: Note) = noteDatabaseDao.updateNote(note = note)
    suspend fun deleteNote(note: Note) = noteDatabaseDao.deleteNotes(note = note)
    fun getAllNotes():Flow<List<Note>> = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()
}