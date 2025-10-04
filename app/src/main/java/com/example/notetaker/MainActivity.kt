package com.example.notetaker

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notetaker.EditScreen
import com.example.notetaker.ui.theme.NoteTakerTheme
import com.example.notetaker.ui.theme.Typography


val NoteListScreen = "NoteList"
val EditScreen = "Edit"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteTakerTheme {
                val noteList = rememberSaveable { mutableListOf<String>() }
                var currentScreen by rememberSaveable { mutableStateOf(NoteListScreen) }
                var selectedNote = ""
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when(currentScreen){
                        NoteListScreen -> {
                            NoteListScreen(
                                modifier = Modifier.padding(innerPadding),
                                noteList = noteList,
                                onNoteClick = { text ->
                                    selectedNote = text
                                    currentScreen = EditScreen
                                }
                            )
                        }
                        EditScreen -> {
                            EditScreenContent(
                                modifier = Modifier.padding(innerPadding),
                                currentNote = selectedNote,
                                onSaveClick = { newNote: String ->
                                    val index = noteList.indexOf(selectedNote)
                                    noteList[index] = newNote
                                    currentScreen = NoteListScreen
                                },
                                onCancelClick = {
                                    currentScreen = NoteListScreen
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        fontStyle = Typography.bodyLarge.fontStyle
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteTakerTheme {
        Greeting("Android")
    }
}