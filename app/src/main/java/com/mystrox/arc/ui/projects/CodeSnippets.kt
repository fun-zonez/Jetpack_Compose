package com.mystrox.arc.ui.projects

//import android.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

// TODO: Add titles to Cards and inside card -> code snippets with Copy Button with Direct Copy to Clipboard option and save snippets on files(i.e csv,txt)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeSnippet(){
    val showDialog = remember { mutableStateOf(false) }
    val snippetCode = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Bottom
    ) {
        if (showDialog.value){
            AlertDialog(
                onDismissRequest = {showDialog != showDialog},
//                modifier = TODO(),
//                properties = TODO(),
                content = {
                    TextField(
                        value = snippetCode.value,
                        onValueChange = {
                            snippetCode.value = it
                        },
                    )
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                saveSnippet("new.txt", snippetCode.value)
                            }
                        }
                    ) {
                        Text("Save")
                    }
                }
            )
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.End)
                .padding(bottom = 20.dp, end = 20.dp),
            onClick = {
                showDialog != showDialog
            },
            elevation = FloatingActionButtonDefaults.elevation(10.dp)
        ) {
            Text(
                text = "+"
            )
        }
    }
}

private suspend fun saveSnippet(path: String, text: String) {
    withContext(Dispatchers.IO){
        var file = File(path).writeText(text)
    }
}

private suspend fun ColumnScope.readSnippet(path: String) {
    withContext(Dispatchers.IO){
        var file = File(path).readText()
        return@withContext file

    }
}

@Preview
@Composable
fun CodePreview(){
    CodeSnippet()
}