package com.example.appnote.data

import com.example.appnote.model.Note

class NoteData {
    fun loadNotes():List<Note>{
        return listOf(
            Note(title = "Ramayan", description = "Description about the loyal lord Ram and Sita"),
            Note(title = "Mahabharat", description = "Story about the pandavs and kaurav"),
            Note(title = "little Krishna", description = "Story about the lord krishna during his childhood"),
            Note(title = "Geeta", description = "Books regarding the dharma yudha and kuru yuddha"),
        )
    }
}