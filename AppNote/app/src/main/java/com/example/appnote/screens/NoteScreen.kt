package com.example.appnote.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appnote.R
import com.example.appnote.components.NoteButton
import com.example.appnote.components.NoteInputText
import com.example.appnote.data.NoteData
import com.example.appnote.model.Note

@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit

) {
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        SmallTopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }, actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon")
        }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.LightGray))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                modifier = Modifier.padding(9.dp),
                text = title,
                label = "Title",
                onTextChange = { value ->
                    if (value.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) {
                        title = value
                    }

                })
            NoteInputText(
                modifier = Modifier.padding(9.dp),
                text = description,
                label = "Add a note",
                onTextChange = { value ->
                    if (value.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) {
                        description = value
                    }
                })
            NoteButton(text = "Save", onClick = {
                if (!title.isNullOrEmpty() && !description.isNullOrEmpty()) {
                    //add notes
                    onAddNote(Note(title = title, description = description))
                    title = ""
                    description = ""
                    Toast.makeText(context, "Note Saved", Toast.LENGTH_SHORT).show()
                }
            })

        }

        Divider(modifier = Modifier.padding(10.dp))
        LazyColumn {
            items(notes) { note ->
                NoteRow(note = note, onNoteClick = {
                    onRemoveNote(note)
                })
            }
        }


    }

}


@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClick: (Note) -> Unit
) {
    Surface(
        modifier = modifier
            .padding(6.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        shadowElevation = 6.dp
    ) {
        Column(modifier = modifier
            .clickable {
                onNoteClick(note)
            }
            .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = note.title, style = MaterialTheme.typography.titleMedium)
            Text(text = note.description, style = MaterialTheme.typography.titleSmall)

        }
    }

}


@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(notes = NoteData().loadNotes(), onAddNote = {}, onRemoveNote = {})
}