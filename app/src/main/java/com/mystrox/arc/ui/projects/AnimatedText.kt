package com.mystrox.arc.ui.projects

//import android.graphics.Color
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

// TODO : Implement text animation on font size and rotation and color over time
@Composable
fun AnimatedText(){

    var infiniteTransition = rememberInfiniteTransition("transition")
    var color = infiniteTransition.animateColor(
        targetValue = Color.Green,
        initialValue = Color.Red,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000,
                easing = Ease
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    val scale: State<Float> = infiniteTransition.animateValue(
        targetValue = 5f,
        initialValue = 1f,
        typeConverter = Float.VectorConverter,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = Ease),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "HELLO",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            color = color.value,
            modifier = Modifier
                .scale(scale.value)
        )
    }
}

@Preview
@Composable
fun AniPreview(){
    AnimatedText()
}