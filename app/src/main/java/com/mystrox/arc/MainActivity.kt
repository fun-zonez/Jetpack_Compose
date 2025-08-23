package com.mystrox.arc

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mystrox.arc.ui.ProjectCard
import com.mystrox.arc.ui.projects.Attempt1
import com.mystrox.arc.ui.projects.Calculator
import com.mystrox.arc.ui.projects.CodeSnippet
import com.mystrox.arc.ui.projects.OxygenUi
import com.mystrox.arc.ui.projects.SnippetScreen
import com.mystrox.arc.ui.projects.StringAno
import com.mystrox.arc.ui.projects.WebViewX
import com.mystrox.arc.ui.theme.ArcTheme
import com.mystrox.arc.ui.theme.DarkMyColors
import com.mystrox.arc.ui.theme.LightMyColors

// TODO : USE projectList in items instead of item in LazyVerticalGrid
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArcTheme() {
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
        composable(Routes.Project3.route) {
            Calculator()
        }
        composable(Routes.Project4.route) {
            WebViewX()
        }
        composable(Routes.Project5.route) {
            CodeSnippet(navController)
        }
        composable(
            route ="SnippetScreen/{mine}",
            arguments = listOf(navArgument("mine") { type = NavType.StringType })
        )
        {
            val mine = it.arguments?.getString("mine")
            SnippetScreen(mine?.split(","))
        }
        composable(Routes.Project6.route) {
            MaterialTheme(
                colorScheme = if(isSystemInDarkTheme()) DarkMyColors else LightMyColors,
                typography = MaterialTheme.typography

            ) {
                OxygenUi()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
    var mdC = MaterialTheme.colorScheme
    var projectList = mapOf(
        1 to "Attempt 1,17-08-2025",
        2 to "String Ano,17-08-2025",
        3 to "Calculator,19-08-2025",
        4 to "WebViewX,20-08-2025",
        5 to "Code Snippet,23-08-2025"
    )
//    var projectListSplit = projectList.values.
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Arc",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxSize(),
//                            .padding(top = 15.dp),
                        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                        color = mdC.onTertiary,
                        fontFamily = FontFamily(
                            Font(R.font.funnel_display_variable_font_wght, FontWeight.Bold)
                        )
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = mdC.background
//                    titleContentColor = Color.Black
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
                    .background(mdC.background)
//            .verticalScroll(state = rememberScrollState())
                    .padding(
//                        top = 40.dp,
                        start = 30.dp, end = 30.dp),
                verticalArrangement = Arrangement.Top,
                horizontalArrangement = Arrangement.Center,
                columns = GridCells.Adaptive(100.dp)
            ) {

//                items(4){
//                    ProjectCard(ProjNum = it,",navController = navController)
//                }
                item {
                    ProjectCard(1, "Grids","17-08-2025", navController = navController)
                }
                item {

                    ProjectCard(2, "String Ano","17-08-2025", navController = navController)

                }
                item {

                    ProjectCard(3, "Calculator","19-08-2025", navController = navController)

                }
                item {
                    ProjectCard(
                        4,
                        "WebViewX",
                        "20-08-2025",
                        navController = navController
                    )
                }
                item {
                    ProjectCard(
                        5,
                        "Code Snippet",
                        "23-08-2025",
                        navController = navController
                    )
                }
                item{
                    ProjectCard(
                        6,
                        "System UI",
                        "23-08-2025",
                        navController = navController
                    )
                }
            }
        }
}


sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object Project1 : Routes("project1")
    data object Project2 : Routes("project2")
    data object Project3 : Routes("project3")
    data object Project4 : Routes("project4")
    data object Project5 : Routes("project5")
    data object Project6 : Routes("project6")
}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArcTheme(darkTheme = false) {
        Home(navController = rememberNavController())
//        Calculator()
    }
}