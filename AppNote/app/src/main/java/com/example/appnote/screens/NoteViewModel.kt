package com.example.appnote.screens

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.appnote.data.NoteData
import com.example.appnote.model.Note

class NoteViewModel : ViewModel() {


    private var noteList = mutableStateListOf<Note>()

    init {
        noteList.addAll(NoteData().loadNotes())
    }

    fun addNote(note: Note) {
        noteList.add(note)
    }

    fun removeNote(note: Note) {
        noteList.remove(note)
    }

    fun getAllNotes(): List<Note> {
        return noteList
    }

}