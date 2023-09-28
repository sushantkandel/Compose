package com.example.appnote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appnote.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDatabaseDao(): NoteDatabaseDao
}