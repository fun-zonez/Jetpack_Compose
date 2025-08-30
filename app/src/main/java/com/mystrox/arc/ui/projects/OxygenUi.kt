package com.mystrox.arc.ui.projects

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.media.AudioManager
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.mystrox.arc.R
import com.mystrox.arc.ui.theme.DarkMyColors
import com.mystrox.arc.ui.theme.LightMyColors
import java.time.LocalDate
import java.time.LocalTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OxygenUi() {

    fun Context.findActivity(): Activity? {
        var context = this
        while (context is ContextWrapper) {
            if (context is Activity) return context
            context = context.baseContext
        }
        return null
    }

    val context = LocalContext.current
    val activity = context.findActivity() ?: return
    val window = activity.window
    val insetsController = WindowCompat.getInsetsController(window, window.decorView)

    DisposableEffect(Unit) {
        insetsController.apply {
            hide(WindowInsetsCompat.Type.statusBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
        }

        onDispose {
            insetsController.apply {
                show(WindowInsetsCompat.Type.statusBars())
                systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
            }
        }
    }


    val darktheme = isSystemInDarkTheme()
    var isdarktheme by remember { mutableStateOf(darktheme) }
    MaterialTheme(
        colorScheme = if (isdarktheme) DarkMyColors else LightMyColors,
        typography = MaterialTheme.typography

    ) {
        val uiStates = remember {
            mutableStateListOf(true, false, false, true, false, true, false, true)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorScheme.background)
                .padding(top = 12.dp, end = 23.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
//                    .padding(bottom = 10.dp)
            ) {
                Row(
                    modifier = Modifier
//                        .width(50.dp)
                        .offset(y = 40.dp, x = 25.dp)
                ) {
                    val time =
                        LocalTime.now().hour.toString() + ":" + LocalTime.now().minute.toString() + " " + LocalDate.now().dayOfWeek.toString()
                            .substring(0, 3).lowercase()
                            .replaceFirstChar { it.uppercase() } + ", " + LocalDate.now().dayOfMonth + " " + LocalDate.now().month.toString()
                            .substring(0, 3).lowercase().replaceFirstChar { it.uppercase() }
                    Text(
                        time,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Start,
                        color = colorScheme.primary
                    )
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
//                        horizontalArrangement = Arrangement.End
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.circle),
                            contentDescription = "",
                            tint = colorScheme.primary,
                            modifier = Modifier
                                .size(10.dp)
                                .offset(x = (-41.2).dp, y = 9.8.dp)
                                .rotate(30f)
                        )
                        Icon(
                            painter = painterResource(R.drawable.hexagon),
                            contentDescription = "",
                            tint = colorScheme.primary,
                            modifier = Modifier
                                .size(23.dp)
                                .offset(x = (-35).dp, y = 3.dp)
                                .rotate(30f)
                        )
                    }

                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        Icons.Filled.Notifications,
                        modifier = Modifier
                            .size(15.dp)
                            .offset(y = (3).dp),
                        contentDescription = "Notifications",
                        tint = colorScheme.primary
                    )
                    Box {
                        Text(
                            " 22.0  ",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = colorScheme.primary,
                            modifier = Modifier.offset(x = 5.dp, y = (-4.5).dp)
                        )
                        Text(
                            "  KB/S  ",
                            fontSize = 8.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = colorScheme.primary,
                            modifier = Modifier
                                .offset(x = 5.dp, y = (4).dp)

                        )
                    }
                    Box {
                        Text(
                            "Vo",
                            fontSize = 8.sp,
                            color = colorScheme.primary,
                            modifier = Modifier.offset(x = 5.dp, y = (-2).dp)
                        )
                        Icon(
                            painter = painterResource(R.drawable.lte),
                            contentDescription = "bold",
                            modifier = Modifier
                                .size(22.dp)
                                .padding(top = 9.dp),
                            tint = colorScheme.primary
                        )
                    }
                    Box {
                        Text(
                            "5G",
                            fontSize = 8.sp,
                            color = colorScheme.primary,
                            modifier = Modifier.offset(x = 1.dp, y = (-4.3).dp)
                        )
                        Icon(
                            painter = painterResource(R.drawable.signal),
                            contentDescription = "bold",
                            modifier = Modifier
                                .size(20.dp)
                                .padding(top = 3.dp),
                            tint = colorScheme.primary
                        )
                    }

                    Text(" 30%", color = colorScheme.primary, fontSize = 13.sp)
                }
            }
            Spacer(Modifier.height(60.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
            ) {
                Box(
                    modifier = Modifier
                        .size(145.dp)
                        .offset(x = 18.dp)
                        .background(colorScheme.onBackground, shape = RoundedCornerShape(10.dp))
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            Arrangement.SpaceBetween
                        ) {
                            Image(
                                painter = painterResource(R.drawable.spotifyweb),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(20.dp)
                                    .offset(y = 3.dp)
                                    .clip(shape = RoundedCornerShape(5.dp))
                            )
                            Icon(
                                painter = painterResource(R.drawable.headphone),
                                contentDescription = "",
                                modifier = Modifier.size(24.dp),
                                tint = colorScheme.primary
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text("blue", color = colorScheme.primary)
                            Text("yung kai", color = colorScheme.primary.copy(0.5f))
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.next),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(24.dp)
                                    .rotate(-180f),
                                tint = colorScheme.primary
                            )

                            Icon(
                                painter = painterResource(if (uiStates[7]) R.drawable.pause else R.drawable.play),
                                contentDescription = "",
                                tint = colorScheme.primary,
                                modifier = Modifier
                                    .size(23.dp)
                                    .clickable(
                                        true,
                                        onClick = {
                                            uiStates[7] = !uiStates[7]
                                        }
                                    )
                            )

                            Icon(
                                painter = painterResource(R.drawable.next),
                                contentDescription = "",
                                modifier = Modifier.size(24.dp),
                                tint = colorScheme.primary
                            )
                        }
                    }

                }
                Box(
                    modifier = Modifier
                        .height(145.dp)
                        .width(65.dp)
                        .offset(x = 11.dp)
                        .background(
                            colorScheme.onBackground.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    val brightness = getSystemBrightness150(context)
                    val brightnessPointer = remember { mutableStateOf(brightness.toFloat()) }
                    LaunchedEffect(brightnessPointer.value) {
                        setSystemBrightness(context, brightnessPointer.value.toInt())
                    }

                    Box(
                        modifier = Modifier
                            .height(brightnessPointer.value.dp)
                            .width(90.dp)
//                            .offset(x = 11.dp)
                            .clip(
                                shape = RoundedCornerShape(
                                    bottomStart = 10.dp,
                                    bottomEnd = 10.dp,
                                    topStart = if (brightnessPointer.value >= 140f) 10.dp else 0.dp,
                                    topEnd = if (brightnessPointer.value >= 140f) 10.dp else 0.dp
                                )
                            )
                            .background(Color.White)
                            .pointerInput(Unit) {
                                detectDragGestures { change, dragAmount ->
                                    change.consume()
                                    brightnessPointer.value =
                                        (brightnessPointer.value - dragAmount.y).coerceIn(0f, 150f)
                                    println(brightnessPointer.value)

                                }
                            }

                    )
                    Icon(
                        painter = painterResource(R.drawable.brightness),
                        modifier = Modifier
                            .offset(x = (-22).dp, y = (-18).dp)
                            .size(22.dp),
                        contentDescription = "",
                        tint = Color.Black.copy(alpha = 0.8f)
                    )
                }
                Box(
                    modifier = Modifier
                        .height(145.dp)
                        .width(65.dp)
                        .offset(x = 11.dp)
                        .background(
                            colorScheme.onBackground.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    val volume = getSystemVolume(context)
                    val volumePointer = remember { mutableStateOf(volume.toFloat()) }
                    Box(
                        modifier = Modifier
                            .height(volumePointer.value.dp)
                            .width(90.dp)
//                            .offset(x = 11.dp)
                            .clip(
                                shape = RoundedCornerShape(
                                    bottomStart = 10.dp,
                                    bottomEnd = 10.dp,
                                    topStart = if (volumePointer.value >= 140f) 10.dp else 0.dp,
                                    topEnd = if (volumePointer.value >= 140f) 10.dp else 0.dp
                                )
                            )
                            .background(Color.White)
                            .pointerInput(Unit) {
                                detectDragGestures { change, dragAmount ->
                                    change.consume()
                                    volumePointer.value =
                                        (volumePointer.value - dragAmount.y).coerceIn(0f, 150f)
                                    println(volumePointer.value)
                                    setVolume(context, volumePointer.value.toInt())

                                }
                            }
                    )
                    Icon(
                        painter = painterResource(R.drawable.speakeri),
                        modifier = Modifier
                            .offset(x = (-18).dp, y = (-18).dp)
                            .size(28.dp),
                        contentDescription = "",
                        tint = Color.Black.copy(alpha = 0.8f)
                    )

                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .height(65.dp)
                        .width(145.dp)
                        .offset(x = 11.dp)
                        .clickable(
                            true,
                            onClick = {
                                uiStates[0] = !uiStates[0]
                                isdarktheme = !isdarktheme

                            }
                        )
                        .background(
                            if (uiStates[0]) colorScheme.secondary else colorScheme.onBackground,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.darkmode),
                            contentDescription = "",
                            tint = if (uiStates[0]) colorScheme.onSurface else colorScheme.primary,
                            modifier = Modifier
                                .padding(start = 15.dp)
                                .rotate(40f)
                        )
                        Text(
                            "   Dark mode",
                            color = if (uiStates[0]) colorScheme.onSurface else colorScheme.primary,
                            fontSize = 13.sp
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .height(65.dp)
                        .width(150.dp)
                        .offset(x = 11.dp)
                        .clickable(
                            true,
                            onClick = {
                                uiStates[1] = !uiStates[1]
                            }
                        )
                        .background(
                            if (uiStates[1]) colorScheme.secondary else colorScheme.onBackground,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row {
                        Icon(
                            painter = painterResource(R.drawable.wifi),
                            contentDescription = "",
                            tint = if (uiStates[1]) colorScheme.onSurface else colorScheme.primary,
                            modifier = Modifier.padding(start = 15.dp)
                        )
                        Text(
                            "   Wi-Fi",
                            color = if (uiStates[1]) colorScheme.onSurface else colorScheme.primary,
                            fontSize = 12.sp
                        )
                        Icon(
                            Icons.Default.KeyboardArrowUp,
                            contentDescription = "",
                            tint = if (uiStates[1]) colorScheme.onSurface else colorScheme.primary,
                            modifier = Modifier
                                .padding(start = 45.dp)
                                .rotate(90f)
                                .background(Color.White.copy(alpha = 0.2f), shape = CircleShape)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.padding(start = 20.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .offset(x = 12.dp)
                        .clickable(
                            true,
                            onClick = {
                                uiStates[2] = !uiStates[2]
                            }
                        )
                        .background(
                            if (uiStates[2]) colorScheme.secondary else colorScheme.onBackground,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.bluetooth),
                        contentDescription = "",
                        tint = if (uiStates[2]) colorScheme.onSurface else colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .offset(x = 25.dp)
                        .clickable(
                            true,
                            onClick = {
                                uiStates[3] = !uiStates[3]
                            }
                        )
                        .background(
                            if (uiStates[3]) colorScheme.secondary else colorScheme.onBackground,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.auto_rotate),
                        contentDescription = "",
                        tint = if (uiStates[3]) colorScheme.onSurface else colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .offset(x = 38.dp)
                        .clickable(
                            true,
                            onClick = {
                                uiStates[4] = !uiStates[4]
                            }
                        )
                        .background(
                            if (uiStates[4]) colorScheme.secondary else colorScheme.onBackground,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.location),
                        contentDescription = "",
                        tint = if (uiStates[4]) colorScheme.onSurface else colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .offset(x = 52.dp)
                        .clickable(
                            true,
                            onClick = {
                                uiStates[5] = !uiStates[5]
                            }
                        )
                        .background(
                            if (uiStates[5]) colorScheme.secondary else colorScheme.onBackground,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.dnd),
                        contentDescription = "",
                        tint = if (uiStates[5]) colorScheme.onSurface else colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .offset(x = 65.dp)
                        .clickable(
                            true,
                            onClick = {
                                uiStates[6] = !uiStates[6]
                            }
                        )
                        .background(
                            if (uiStates[6]) colorScheme.secondary else colorScheme.onBackground,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.neural),
                        contentDescription = "",
                        tint = if (uiStates[6]) colorScheme.onSurface else colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .width(49.dp)
                    .height(4.dp)
                    .offset(x = 158.dp)
                    .background(colorScheme.onBackground, shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {}
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(start = 28.dp)
//                    .offset(x = 158.dp)
                    .background(
                        colorScheme.onBackground.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(15.dp)
                    ),
//                contentAlignment = Alignment.Center
            ) {
                Column {
                    Row(
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.whatsapp),
                            contentDescription = "",
                            tint = colorScheme.primary,
                            modifier = Modifier
                                .padding(start = 15.dp, top = 8.dp)
                                .size(15.dp)
                        )
                        Text(
                            "  WhatsApp • Dhinesh • 2m",
                            modifier = Modifier.padding(top = 5.dp),
                            color = colorScheme.primary,
                            fontSize = 12.sp
                        )
                        Icon(
                            Icons.Default.KeyboardArrowUp,
                            contentDescription = "",
                            tint = colorScheme.primary,
                            modifier = Modifier
                                .padding(start = 125.dp, top = 5.dp)
                                .background(
                                    Color.White.copy(alpha = 0.05f),
                                    shape = RoundedCornerShape(15.dp)
                                )
                        )

                    }
                    Row {
//                        Icon(
//                            painter = painterResource(R.drawable.account),
//                            contentDescription = "",
//                            tint = colorScheme.primary,
//                            modifier = Modifier
//                                .padding(start = 25.dp, top = 25.dp)
//                                .size(25.dp)
//                        )
                        Text(
                            " D ",
                            color = colorScheme.primary,
                            modifier = Modifier
                                .padding(start = 10.dp, top = 25.dp)
                                .background(
                                    Color.White.copy(alpha = 0.05f),
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            fontSize = 20.sp
                        )
                        Column {
                            Text(
                                "Hi 👋",
                                modifier = Modifier.padding(start = 15.dp, top = 5.dp),
                                color = colorScheme.primary,
                                fontSize = 12.sp
                            )
                            Text(
                                "How are you...",
                                modifier = Modifier.padding(start = 15.dp),
                                color = colorScheme.primary,
                                fontSize = 12.sp
                            )
                            Text(
                                "Naa than da Leo...Leo Das ❤️‍🔥",
                                modifier = Modifier.padding(start = 15.dp),
                                color = colorScheme.primary,
                                fontSize = 12.sp
                            )
                        }
                    }
                    Row {
                        Text(
                            "  Reply  ",
                            color = colorScheme.primary,
                            modifier = Modifier
                                .padding(start = 10.dp, top = 2.dp)
                                .background(
                                    Color.White.copy(alpha = 0.05f),
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            fontSize = 10.sp
                        )
                        Text(
                            "  Mark as read  ",
                            color = colorScheme.primary,
                            modifier = Modifier
                                .padding(start = 25.dp, top = 2.dp)
                                .background(
                                    Color.White.copy(alpha = 0.05f),
                                    shape = RoundedCornerShape(10.dp)
                                ),
                            fontSize = 10.sp
                        )
                        Text(
                            "  Mute  ",
                            color = colorScheme.primary,
                            modifier = Modifier
                                .padding(start = 25.dp, top = 2.dp)
                                .background(
                                    Color.White.copy(alpha = 0.05f),
                                    shape = RoundedCornerShape(15.dp)
                                ),
                            fontSize = 10.sp
                        )
                    }
                }
            }

        }
    }
}

fun getSystemBrightness150(context: Context): Int {
    val systemBrightness = Settings.System.getInt(
        context.contentResolver,
        Settings.System.SCREEN_BRIGHTNESS,
        125 // default
    )
    println(systemBrightness)
    return mapRange(systemBrightness, 0, 255, 0, 150)
}

fun mapRange(value: Int, fromMin: Int, fromMax: Int, toMin: Int, toMax: Int): Int {
    return ((value - fromMin) * (toMax - toMin) / (fromMax - fromMin)) + toMin
}


fun getSystemVolume(context: Context): Int {
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
    println("volume:" + volume)
    return volume
}

fun setVolume(context: Context, level: Int) {
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
    val newVolume = level.coerceIn(0, maxVolume) // prevent crash
    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, newVolume, 0)
}

fun setSystemBrightness(context: Context, level150: Int) {
    if (Settings.System.canWrite(context)) {
        val systemLevel = mapRange(level150, 0, 150, 0, 255)
        Settings.System.putInt(
            context.contentResolver,
            Settings.System.SCREEN_BRIGHTNESS,
            systemLevel
        )
    } else {
        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
        intent.data = ("package:" + context.packageName).toUri()
        context.startActivity(intent)
    }
}


@Preview(showBackground = true)
@Composable
fun OxygenDark() {
    MaterialTheme(
        colorScheme = DarkMyColors

    ) {
        OxygenUi()
    }
}

@Preview(showBackground = true)
@Composable
fun OxygenLight() {
    MaterialTheme(
        colorScheme = LightMyColors
    ) {
        OxygenUi()
    }
}
