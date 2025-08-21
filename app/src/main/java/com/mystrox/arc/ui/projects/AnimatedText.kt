package com.mystrox.arc.ui.projects

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// TODO : Implement text animation on font size and rotation and color over time
@Composable
fun AnimatedText(){
    var fonte = remember {
        mutableStateOf(20.dp)
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
//        Text(
//            text = "Hiii!",
//            fontSize = fonte.value
//        )
    }
}