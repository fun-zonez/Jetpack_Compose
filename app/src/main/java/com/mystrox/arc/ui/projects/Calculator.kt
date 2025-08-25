package com.mystrox.arc.ui.projects

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mystrox.arc.R
import com.mystrox.arc.ui.theme.ArcTheme
import net.objecthunter.exp4j.ExpressionBuilder

@Composable
fun Calculator(){
    var fontFam  = FontFamily(
    Font(resId = R.font.seven_segment)
    )

    var symbols = listOf<String>(
        "AC",
        "%",
        "C",
        "/",
        "7",
        "8",
        "9",
        "*",
        "4",
        "5",
        "6",
        "-",
        "1",
        "2",
        "3",
        "+",
        "00",
        "0",
        ".",
        "="
    )

    var text by remember { mutableStateOf("") }
    var scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top = 50.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(0.2f)
        )
            Text(
                text = text,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(155.dp)
                    .padding(30.dp)
                    .verticalScroll(scrollState, enabled = true)
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
//                        shape = TODO("Make it rectangle Button")
                    ),
            maxLines = 1,
                textAlign = TextAlign.Right,
                overflow = TextOverflow.Visible,
                style = TextStyle(
                    fontFamily = fontFam,
                    fontSize = 45.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )


        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
//            contentPadding = PaddingValues(20.dp),
        ) {
            itemsIndexed(symbols){ index, symb ->
                Button(
                    onClick = {
                        if (index == 0){
                            text = ""
                        }
                        else if (index == 2){
                            if(text.isNotEmpty()){
                                text = text.take(text.lastIndex)
                            }
                        }
                        else if (index == 19){
                            text = expEv(text).toString()
                        }else{
                            text += symb
                        }

//                        scrollState.animateScrollTo(scrollState.maxValue)

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.background
                    ),
                    shape = ButtonDefaults.outlinedShape,
                    modifier = Modifier
                        .size(250.dp,70.dp)
//                        .clip(shape = RectangleShape)
//                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Text(
                        text = symb,
                        fontSize = 30.sp,
                        fontFamily = fontFam,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
    }
}


fun expEv(expression :String): Any {
    try {
        val expressionBuilder = ExpressionBuilder(expression).build()
        return expressionBuilder.evaluate()
    }
    catch (e: Exception){
        Log.e("Expression Builder: ", e.toString())
    }
    return "Error"
}


@Preview
@Composable
fun CalcPreview(){
    ArcTheme {
        Calculator()
    }
}