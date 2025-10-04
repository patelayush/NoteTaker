package com.example.notetaker

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun NoteListScreen(modifier: Modifier = Modifier, noteList: MutableList<String>, onNoteClick:(String) -> Unit){
    var note by remember { mutableStateOf(TextFieldValue("")) }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = note,
                onValueChange = { newNote: TextFieldValue ->
                    note = newNote
                },
                modifier = Modifier.weight(1f).fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        // search the note
                    }
                ),
                isError = note.text.isEmpty(),
                label = {
                    Text("Note")
                },
            )
            Button(
                onClick = {
                    noteList.add(note.text)
                    note = TextFieldValue("")
                    Log.d("notetaker", "notelist size: ${noteList.size}")
                },
                shape = RoundedCornerShape(10.dp),
                enabled = note.text.isNotBlank(),
                elevation = ButtonDefaults.elevatedButtonElevation(5.dp),
            ){
                Icon(
                    imageVector = Icons.Default.Add,
                    modifier = Modifier.padding(5.dp).size(20.dp),
                    contentDescription = "Add",
                    tint = Color.Blue
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            noteList.forEachIndexed{ index, text ->
                Text(
                    text = text,
                    modifier = Modifier.clickable{
                        onNoteClick(text)
                    }
                )
                if(index != noteList.lastIndex) {
                    HorizontalDivider(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}