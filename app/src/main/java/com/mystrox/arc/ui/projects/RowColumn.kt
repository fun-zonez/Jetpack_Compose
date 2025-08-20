package com.mystrox.arc.ui.projects

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mystrox.arc.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Attempt1() {
    val largeRadialGradient = object : ShaderBrush() {
        override fun createShader(size: Size): Shader {
            val biggerDimension = maxOf(size.height, size.width)
            return RadialGradientShader(
                colors = listOf(Color(0xFF2be4dc), Color(0xFF243484)),
                center = size.center,
                radius = biggerDimension / 2f,
                colorStops = listOf(0f, 0.95f)
            )
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CenterAlignedTopAppBar(
            title = { Text(text = "Arc", fontSize = 45.sp, fontWeight = FontWeight.ExtraBold) },
            colors = TopAppBarColors(
                containerColor = Color(0xFF243484),
                titleContentColor = Color.White,
                scrolledContainerColor = Color.Black,
                navigationIconContentColor = Color.Black,
                actionIconContentColor = Color.Black,
            )) }
    ) { innerPadding ->
        Row(
            modifier = Modifier.fillMaxSize()
                .background(brush = largeRadialGradient
                )
                .padding(innerPadding)
                .padding(top = 50.dp, bottom = 100.dp, start = 50.dp, end = 50.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(Modifier.weight(1f).fillMaxHeight(),verticalArrangement = Arrangement.SpaceEvenly, horizontalAlignment = Alignment.CenterHorizontally) {


                CardImage("Hello")
                CardImage("Hello")
                CardImage("Hello")
                CardImage("Hello")
            }

            Spacer(modifier = Modifier.width(30.dp))

            Column( Modifier.weight(1f).fillMaxHeight(),verticalArrangement = Arrangement.SpaceEvenly,horizontalAlignment = Alignment.CenterHorizontally) {
                CardImage("World")
                CardImage("World")
                CardImage("World")
                CardImage("World")
            }
        }
    }
}

@Composable
fun CardImage(text: String){
    Card(
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(4.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Box(contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .background(Color.White)
                .background(brush = Brush.verticalGradient(
                    listOf(Color.Transparent, Color.Black),
                    startY = 200f,
                    endY = 350f)
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Arc Logo",
                modifier = Modifier.size(100.dp)
            )
            Text(text, color = Color.White)
        }
    }
}