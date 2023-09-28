package com.example.appnote.di

import android.content.Context
import androidx.room.Room
import com.example.appnote.data.NoteDatabase
import com.example.appnote.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun providesNoteDao(noteDatabase: NoteDatabase): NoteDatabaseDao =
        noteDatabase.noteDatabaseDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(context, NoteDatabase::class.java, name = "notes_db")
            .fallbackToDestructiveMigration()
            .build()
}