package com.example.appnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appnote.data.NoteData
import com.example.appnote.screens.NoteScreen
import com.example.appnote.screens.NoteViewModel
import com.example.appnote.ui.theme.AppNoteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val noteViewModel: NoteViewModel by viewModels()
                    NoteApp(noteViewModel)

                }
            }
        }
    }
}


@Composable
fun NoteApp(noteViewModel: NoteViewModel = viewModel()) {
    NoteScreen(notes = noteViewModel.getAllNotes(),
        onAddNote = {
            noteViewModel.addNote(it)
        },
        onRemoveNote = {
            noteViewModel.removeNote(it)
        })
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppNoteTheme {
        NoteScreen(notes = NoteData().loadNotes(),
            onAddNote = {},
            onRemoveNote = {})

    }
}