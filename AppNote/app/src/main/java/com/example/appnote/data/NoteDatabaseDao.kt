package com.example.appnote.data

import androidx.room.*
import com.example.appnote.model.Note
import kotlinx.coroutines.flow.Flow
@Dao
interface NoteDatabaseDao {
    @Query("SELECT * from notes_tbl")
    fun getNotes(): Flow<List<Note>>

    @Query("SELECT * from notes_tbl where id=:id")
    suspend fun getNoteById(id: String): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)


    @Delete
    suspend fun deleteNotes(note: Note)


}
