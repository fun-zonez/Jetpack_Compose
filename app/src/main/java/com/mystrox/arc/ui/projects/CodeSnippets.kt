package com.mystrox.arc.ui.projects


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mystrox.arc.ui.theme.ArcTheme
import com.opencsv.CSVReader
import com.opencsv.CSVWriter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileReader
import java.io.FileWriter

// TODO: Add titles to Cards and inside card -> code snippets with Copy Button with Direct Copy to Clipboard option and save snippets on files(i.e csv,txt)


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CodeSnippet(navController: NavController){
    var showDialog by remember { mutableStateOf(false) }
    var snippetCode: MutableList<Array<String>> = remember { mutableListOf() }
    val coroutineScope = rememberCoroutineScope()
    var snippedLoaded by remember { mutableStateOf(false) }
    val file = File(LocalContext.current.filesDir,"snippet.csv")
    if(!file.exists()){
        file.createNewFile()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize()
            .background(colorScheme.background),
        floatingActionButton = {
            FloatingActionButton(
                containerColor = colorScheme.primary,
                modifier = Modifier
                    .padding(bottom = 20.dp, end = 20.dp),
                onClick = {
                    showDialog = true
                },
                elevation = FloatingActionButtonDefaults.elevation(10.dp)
            ) {
                Text(
                    text = "+",
                    fontSize = 30.sp
                )
            }
        }
    ) {paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            coroutineScope.launch {
                snippetCode = readSnippet(file)
                snippedLoaded = true
            }
            if(snippedLoaded){

                items(items = snippetCode){
                    Card(
                        modifier = Modifier.fillMaxWidth()
                            .height(50.dp)
                            .clickable(true){
                                navController.navigate("SnippetScreen/${it[0]},${it[1]}")
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = colorScheme.primary
                        )
                    ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
//                            verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Text(
                                    it[0].toString(),
                                    textAlign = TextAlign.Center,
                                    color = colorScheme.background,
                                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                                )
                            }
//                        }

                    }
                }
            }

        }

        if (showDialog){
            val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
            var snippetTitle by remember { mutableStateOf("") }
            var snippetText by remember { mutableStateOf("") }
            ModalBottomSheet(
                onDismissRequest = { showDialog = false },
                sheetState = sheetState,
                containerColor = colorScheme.surface.copy( alpha = 0.95f ),
                dragHandle = { BottomSheetDefaults.DragHandle(
                    color = colorScheme.onPrimary
                ) },
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                        .background(colorScheme.surface)
                        .padding(top = 15.dp)
                ) {
                    Text(
                        "Add Snippet",
                        fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
                        fontSize = 40.sp,
                        fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                        color = colorScheme.onSurface
                    )
                    Spacer(Modifier.height(20.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        SnippetTextField(snippetTitle, onTextChange = {snippetTitle = it}, singleLine = true)
                        Spacer(modifier = Modifier.height(10.dp))
                        SnippetTextField(
                            snippetText,
                            onTextChange = { snippetText = it },
                        )
                        Button(
                            onClick = {
                                if (snippetTitle.isNotEmpty() && snippetText.isNotEmpty()) {
                                    coroutineScope.launch {
                                        val written = writeSnippet(arrayOf(snippetTitle, snippetText),file)
                                        if (written){
                                            showDialog = false
//                                            snippetCode = readSnippet(file) Todo: fix flow of added new
                                        }
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorScheme.background,
                            ),
                            modifier = Modifier.padding(top = 20.dp)
                        ) {
                            Text(
                                text = "Add",
                                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                                color = colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private suspend fun readSnippet(file: File) : MutableList<Array<String>> =

    withContext(Dispatchers.IO){

        val fileContent = CSVReader(FileReader(file))
        val allRows : MutableList<Array<String>> = fileContent.use {
            it.readAll()
        }
        fileContent.close()
        allRows
    }


private suspend fun writeSnippet(text: Array<String>,file: File) : Boolean =

    withContext(Dispatchers.IO){
//        Log.i("writeSnippet", "inside writeSnippet: $text")
        val fileContent = CSVWriter(FileWriter(file,true))
        fileContent.writeNext(text)
        fileContent.flush()
        fileContent.close()
        true

    }

@Composable
fun SnippetTextField(text: String, onTextChange: (String) -> Unit, modifier: Modifier = Modifier,singleLine: Boolean = false){

    TextField(

        value = text,
        onValueChange = {
            onTextChange(it)
        },
        singleLine = singleLine,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorScheme.background,
            unfocusedContainerColor = colorScheme.background
        )
        ,modifier = modifier

    )

}

@Composable
fun SnippetScreen(it: List<String>?){
    ArcTheme {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            it?.first()?.let { text -> Text(text,fontSize = 20.sp) }
            it?.last()?.let { text -> Text(text, fontSize = 20.sp) }
        }
    }
}
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun CodePreview(){
    ArcTheme(darkTheme = true) {
        CodeSnippet(rememberNavController())
    }

}
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun CodePreview2(){
    ArcTheme(darkTheme = false) {
        CodeSnippet(rememberNavController())
    }

}