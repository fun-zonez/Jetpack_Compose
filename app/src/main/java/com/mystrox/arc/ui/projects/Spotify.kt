package com.mystrox.arc.ui.projects

import android.R.attr.rotation
import android.system.Os.link
import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mystrox.arc.R
import com.mystrox.arc.ui.theme.SpotifyDark
import com.mystrox.arc.ui.theme.SpotifyLight
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SpotifySplash(
    navController: NavHostController
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ){
        Icon(painter = painterResource(id = R.drawable.spotifypng), tint = MaterialTheme.colorScheme.primary, contentDescription = "")
    }
    LaunchedEffect(true) {
        delay(1500)
        navController.navigate("SpotifyHome")
    }
}

@Composable
fun SpotifyHome(navController: NavHostController) {
    var link:String by remember { mutableStateOf("") }

    var clicked by remember { mutableStateOf(false) }
    val rotation = animateFloatAsState(
            targetValue = if (clicked) 360f else 0f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = LinearEasing
            )
    )
    val buttonClr = animateColorAsState(
        targetValue = if (clicked) Color.Red else Color.Green,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    val buttonSize = animateIntAsState(
        targetValue = if (clicked) 200 else 80,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessVeryLow
        )
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = link,
            onValueChange = {
                link = it
            },
//            placeholder = {Text("Enter Spotify Link")},
            label = {Text("Enter Spotify Playlist Link") },
            colors = TextFieldDefaults.colors(
//                unfocusedLabelColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .padding(top = 100.dp)
                .rotate(rotation.value)
//                .background(color = MaterialTheme.colorScheme.primary)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                clicked = !clicked

            },
            Modifier.width(buttonSize.value.dp),
            colors = ButtonDefaults.buttonColors(containerColor = buttonClr.value)
        ) {
            Text("Look-Up Spotify")
        }
        }
    if (clicked){
        LaunchedEffect(true) {
            delay(2000)
            clicked = !clicked
        }
    }
    }


@Preview
@Composable
fun SpotifyDPreview(){
    MaterialTheme(
        colorScheme = SpotifyDark
    ) {
        SpotifyHome(rememberNavController())
    }
}


@Preview
@Composable
fun SpotifyLPreview(){
    MaterialTheme(
        colorScheme = SpotifyLight
    ) {
        SpotifyHome(rememberNavController())
    }
}