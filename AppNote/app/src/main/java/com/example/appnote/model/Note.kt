package com.example.appnote.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "notes_tbl")
data class Note(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "notes_title")
    val title: String,
    @ColumnInfo(name = "notes_description")
    val description: String,
    @ColumnInfo(name = "notes_date")
    val date: String = Date().toString()

)
