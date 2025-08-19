package com.mystrox.arc

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun StringAno(){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        stringBuild(35,"Hello World from Arc!")
//        stringBuild(35,"This is a test of the new font system in Android")
    }
}
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun stringBuild(fontSize:Int, text_:String){
    var fontfamily = FontFamily(
        Font(R.font.frijole_regular, FontWeight.Light),
        Font(R.font.frijole_regular, FontWeight.Normal),
        Font(R.font.frijole_regular, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.frijole_regular, FontWeight.Medium),
        Font(R.font.funnel_display_variable_font_wght, FontWeight.Bold)
    )
    var ite = 0
    var text_split : List<String> = text_.split(" ").toList()
    var text_clr : List<Color> = listOf(Color.Green, Color.Red, Color.Blue, Color.Cyan, Color.Magenta, Color.Yellow)
    for (i in text_split){
        ite += 1
        Text(text = buildAnnotatedString { withStyle(
            style = SpanStyle(
                color = text_clr[ite % text_clr.size],
                fontSize = fontSize.sp,
                fontFamily = fontfamily,
                fontWeight = FontWeight.Normal
            )
        ){
            append(i[0].toString())
        }
            append(i.substring(1))
        },
            fontSize = (fontSize-10).sp,
            fontFamily = fontfamily,
            fontWeight = FontWeight.Normal
        )
    }
//    Text(text = buildAnnotatedString { withStyle(
//        style = SpanStyle(
//            color = Color.Green,
//            fontSize = fontSize.sp,
//            fontFamily = fontfamily,
//            fontWeight = FontWeight.Normal
//        )
//    ){
//            append("H")
//    }
//    append("ello ")
//        withStyle(
//            style = SpanStyle(
//                color = Color.Red,
//                fontSize = fontSize.sp,
//                fontFamily = fontfamily,
//                fontWeight = FontWeight.Normal
//            )
//        ) {
//            append("W")
//        }
//        append("orld")
//},
//        fontSize = (fontSize-10).sp,
//        fontFamily = fontfamily,
//        fontWeight = FontWeight.Normal)
}
