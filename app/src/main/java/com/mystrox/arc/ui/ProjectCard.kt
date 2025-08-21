package com.mystrox.arc.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mystrox.arc.R


@Composable
fun ProjectCard(
    ProjNum : Int,
    ProjTitle: String,
    date : String,
    navController: NavController
){
    val fontFamily = FontFamily(
        Font(R.font.frijole_regular),
    )
    var holdV by remember {mutableStateOf<Boolean>(false)}
    Column(

        modifier = Modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            //        shape = Shape,
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp, 80.dp)
                .clickable(
                    onClick = {
                        navController.navigate("project$ProjNum")
                    }
                ),
            elevation = cardElevation(10.dp),
            border = BorderStroke(width = 1.dp, color = Color.Black),
            colors = CardDefaults.cardColors(contentColor = Color.White)
        ) {

            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
//                                Brush.linearGradient(
//                        listOf<Color>(
//                            Color(0xFF15159B),
//                            Color.Red
//                        )
//                    )
//                    )
            ) {


                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .fillMaxWidth(),){
                    Text(
                        text = ProjNum.toString(),
                        fontSize = 28.sp,
                        fontFamily=fontFamily,
                        modifier = Modifier
                            .weight(3f)
                            .padding(top = 15.dp),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    //                    Spacer(modifier = Modifier.width(10.dp))
                    IconButton (
                        modifier = Modifier
                            .weight(1f),
                        //                            .padding(bottom = 15.dp),
                        onClick = {
                            holdV= holdV.not()
                        }) {
                        Icon(imageVector = Icons.Default.MoreVert, tint =  MaterialTheme.colorScheme.onPrimary, contentDescription = "", modifier = Modifier.size(18.dp).offset( y = (-10).dp))
                        DropdownMenu(expanded = holdV, onDismissRequest = {holdV=!holdV}) {
                            DropdownMenuItem({ Text("Date: $date") },onClick = {})
                        }
                    }
                }
                Text(text = ProjTitle, color =  MaterialTheme.colorScheme.onPrimary, fontSize = 9.sp, fontFamily = FontFamily( Font(R.font.funnel_display_variable_font_wght)), textAlign = TextAlign.Center)
            }
        }
    }
}