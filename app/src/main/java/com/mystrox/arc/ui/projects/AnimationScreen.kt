package com.mystrox.arc.ui.projects

import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.EaseInCirc
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.EaseInOutBounce
import androidx.compose.animation.core.EaseInOutElastic
import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mystrox.arc.R
import com.mystrox.arc.ui.theme.ArcTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimationScreen(navController: NavHostController) {
    val colorScheme = MaterialTheme.colorScheme
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Animations",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = colorScheme.primary,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorScheme.background.copy(alpha = 0.8f)
                )
            )
        }
    ) { it ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(colorScheme.background)
                .scrollable(scrollState, orientation = Orientation.Vertical)
//            .verticalScroll(scrollState)
        ) {
            items(10) { index ->
                Card(
                    onClick = {
                        navController.navigate("AnimationCard/${index}")
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(100.dp)
                        .padding(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = colorScheme.primary,
//                    contentColor = colorScheme.onPrimary
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "AnimationName",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(
                                Font(R.font.funnel_display_variable_font_wght)
                            )
                        )
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "ArrowDropDown",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AnimationCard(id: Int?) {
    if (id == 0) {
        Anime0()
    }else{
        Anime1()
    }
}


@Composable
fun Anime0() {
    val infiniteTransition = rememberInfiniteTransition()
    val size = infiniteTransition.animateValue(
        initialValue = 80.dp,
        targetValue = 150.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(900, delayMillis = 100, easing = EaseInCirc),
            repeatMode = RepeatMode.Reverse
        )
    )
    val rotation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 365f,
        animationSpec = infiniteRepeatable(
            animation = tween(500,delayMillis = 300, easing = Ease),
//            repeatMode = RepeatMode.Reverse
        )
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.LocationOn,
            contentDescription = "",
            modifier = Modifier
                .rotate(rotation.value)
                .size(size.value),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun Anime1() {
    val colorScheme = MaterialTheme.colorScheme
    val infiniteTransition = rememberInfiniteTransition()
    val height = infiniteTransition.animateValue(
        initialValue = 20.dp,
        targetValue = 150.dp,
        typeConverter = Dp.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = Ease
            )
        )
    )
    Column(
        modifier = Modifier.fillMaxSize()
            .background(colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(colorScheme.onBackground)
                .padding(10.dp)
        )

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(colorScheme.onBackground)
                .padding(10.dp)
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(colorScheme.onBackground)
                .padding((-10).dp)
        )

        Box(
            modifier = Modifier
                .size(100.dp)
                .background(colorScheme.onBackground)
                .padding(10.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AnimationPreview() {
    ArcTheme(darkTheme = true) {
        Anime1()
    }
}