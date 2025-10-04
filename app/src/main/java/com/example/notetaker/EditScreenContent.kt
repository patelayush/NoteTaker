package com.example.notetaker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun EditScreenContent(
    modifier: Modifier = Modifier,
    currentNote: String,
    onSaveClick: (String) -> Unit,
    onCancelClick: () -> Unit,
) {
    var currentNoteLocal by remember { mutableStateOf(TextFieldValue(currentNote)) }
    Column(
        modifier = modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = currentNoteLocal, onValueChange = { note ->
                currentNoteLocal = note
            },
            label = { Text(text = "Current Note") }
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = {
                    onSaveClick(currentNoteLocal.text)
                }
            ) {
                Text(text = "Save")
            }
            Button(
                onClick = {
                    onCancelClick()
                }
            ) {
                Text(text = "Cancel")
            }
        }
    }

}