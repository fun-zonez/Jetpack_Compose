package com.mystrox.arc

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mystrox.arc.ui.theme.ArcTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArcTheme {
                ArcApp()
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ArcApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            Home(navController)
        }
        composable(Routes.Project1.route) {
            Attempt1()
        }
        composable(Routes.Project2.route) {
            StringAno()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Arc",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxSize(),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF243484),
                    titleContentColor = Color.Black
                )
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) { innerPading ->
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPading)
                    .background(Color.Gray)
//            .verticalScroll(state = rememberScrollState())
                    .padding(top = 40.dp, start = 30.dp, end = 30.dp),
                verticalArrangement = Arrangement.Top,
                horizontalArrangement = Arrangement.Center,
                columns = GridCells.Fixed(3)
            ) {
                item {
                    ProjectCard(1, "Attempt 1", navController = navController)
                }
                item {

                    ProjectCard(2, "String Ano", navController = navController)

                }
//                item {
//
//                    ProjectCard(3, "", navController = navController)
//
//                }
//                item {
//
//                    ProjectCard(
//                        4, "Project 5",
//                        navController = navController
//                    )
//
//                }
            }
        }
}

@Composable
fun ProjectCard(ProjNum : Int,ProjTitle: String,navController: NavController){
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
                .background(Brush.linearGradient(
            listOf<Color>(
                Color(0xFF15159B),
                Color.Red
            )
        ))) {


                Row(horizontalArrangement = Arrangement.Center,
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
                        textAlign = TextAlign.Center
                    )
//                    Spacer(modifier = Modifier.width(10.dp))
                    IconButton (
                        modifier = Modifier
                            .weight(1f),
//                            .padding(bottom = 15.dp),
                        onClick = {
                        holdV= holdV.not()
                    }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "", modifier = Modifier.size(18.dp))
                        DropdownMenu(expanded = holdV, onDismissRequest = {holdV=!holdV}) {
                            DropdownMenuItem({ Text("Date: 2023-10-01") },onClick = {})
                        }
                    }
                }
            Text(text = ProjTitle, fontSize = 9.sp, fontFamily = FontFamily( Font(R.font.funnel_display_variable_font_wght)), textAlign = TextAlign.Center)
        }
    }
    }
}

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object Project1 : Routes("project1")
    data object Project2 : Routes("project2")
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArcTheme {
        Home(navController = rememberNavController())
    }
}