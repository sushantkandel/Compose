package com.example.appnote.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appnote.model.Note
import com.example.appnote.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    //  private var noteList = mutableStateListOf<Note>()

    init {

        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.getAllNotes().distinctUntilChanged().collect { listNotes ->
                _noteList.value = listNotes

            }
        }
        //noteList.addAll(NoteData().loadNotes())
    }

    fun addNote(note: Note) = viewModelScope.launch {
        noteRepository.addNote(note = note)
    }

    fun removeNote(note: Note) = viewModelScope.launch {
        noteRepository.deleteNote(note = note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note = note)
    }


}