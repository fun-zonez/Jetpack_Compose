package com.mystrox.arc.ui.projects

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.EaseInCirc
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    val colorScheme = colorScheme
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
    } else if (id == 1) {
        Anime1()
    } else {
        Anime2()
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
            animation = tween(500, delayMillis = 300, easing = Ease),
//            repeatMode = RepeatMode.Reverse
        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Default.LocationOn,
            contentDescription = "",
            modifier = Modifier
                .rotate(rotation.value)
                .size(size.value),
            tint = colorScheme.primary
        )
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun Anime1() {
    val colorScheme = colorScheme
    val composeTips = mapOf(
        "Use remember to cache work" to
                "Wrap expensive calculations or composables in `remember` so they aren’t recomputed on every recomposition. " +
                "For example, computing a list or layout parameters should be done once and remembered, or better yet moved to a ViewModel or @Reusable state object. " +
                "Compose’s best practices advise using `remember` liberally for anything heavy.",

        "Stable keys in lazy lists" to
                "When using LazyColumn/LazyRow, always provide a stable `key` parameter for each item. " +
                "This lets Compose correctly skip recomposing items whose content hasn’t changed. " +
                "Without stable keys, any change (even in one item) can trigger recomposition of many siblings.",

        "Use derivedStateOf for derived values" to
                "Wrap computed or derived state in `derivedStateOf`. This creates a new State that only changes (and triggers recomposition) when its inputs actually change. " +
                "For rapidly changing inputs, `derivedStateOf` avoids unnecessary recompositions of dependent UI. " +
                "If a composable depends on multiple states, derive a single snapshot to minimize updates.",

        "Defer state reads" to
                "Read observable state (e.g., from StateFlow or LiveData) as late as possible. " +
                "Use lambda-based modifiers (like `Modifier.offset { }`) or pass lambdas instead of direct state reads, so Compose can skip unchanged parts more easily. " +
                "Avoid reading state at the top of a composable unless needed — defer it into modifiers or children.",

        "Avoid backward state writes" to
                "Never update one @Composable’s state after reading it in the same pass — this causes infinite loops. " +
                "Don’t write to a state that the current composable already read. This 'backwards write' is explicitly warned against in the Compose docs.",

        "Use rememberUpdatedState for effects" to
                "If you launch side-effects (e.g., LaunchedEffect) with a changing callback or value, wrap that value in `rememberUpdatedState`. " +
                "This ensures the effect isn’t restarted on every recomposition — only when relevant parameters truly change.",

        "Use snapshotFlow for collections" to
                "When you need to treat multiple state changes as a single flow (e.g., coalesce a list of states), use `snapshotFlow { }`. " +
                "This helps convert Compose state to a Flow efficiently without causing recomposition on every tiny change.",

        "Prefer Lazy over Column" to
                "For large or dynamic lists, prefer `LazyColumn`/`LazyRow` over `Column`. " +
                "Lazy layouts only compose visible items, reducing work. Always pair lazy lists with stable keys to maximize efficiency.",

        "Enable Baseline Profiles" to
                "Build in release mode with R8 and Baseline Profiles enabled. A baseline profile precompiles critical Compose paths, significantly speeding startup and smoothness. " +
                "Compose provides a default baseline, but for best performance, create app-specific profiles using Macrobenchmark.",

        "Trace and Profile Compose" to
                "Use Android Studio’s profilers with Composition Tracing. Add `androidx.compose.runtime:runtime-tracing` dependency (Flamingo+/Compose 1.3+). " +
                "Record system traces to see individual composables in the flame chart. Double-click a trace event to jump to the source.",

        "Inspect recomposition counts" to
                "In Android Studio’s Layout Inspector (Compose mode), enable recomposition counters. " +
                "It shows per-node recompose and skip counts with colored overlays, helping identify hotspots. " +
                "Frequent recompositions may indicate unstable state or missing `remember`.",

        "Check Compose compiler reports" to
                "Enable Compose compiler stability reports in Gradle:\n" +
                "```kotlin\n" +
                "composeCompiler {\n" +
                "    reportsDestination = layout.buildDirectory.dir(\"compose_compiler\")\n" +
                "}\n" +
                "```\n" +
                "Reports show which composables are skippable and which parameters are unstable — great for diagnosing unexpected recomposition.",

        "Minimize draw/layout work" to
                "Break large composables into smaller ones so Compose can skip more granularly. " +
                "Use appropriate UI elements — avoid nested Box/Column if a simpler layout works. " +
                "Prefer GPU-accelerated modifiers (e.g., `.blur()` vs manual drawing) when possible.",

        "Use CompositionLocal or ViewModel for shared state" to
                "For shared state (like theme or navigation), use a single ViewModel or `CompositionLocal` to avoid prop-drilling. " +
                "Passing props through many layers can inadvertently trigger parent recompositions.",

        "Use Live Templates in Android Studio" to
                "Speed up coding with Compose Live Templates:\n" +
                "- `comp` → @Composable stub\n" +
                "- `prev` → @Preview scaffold\n" +
                "- `paddp`, `weight` → common modifiers\n" +
                "- `W`, `WR`, `WC` → wrap in Row/Column\n" +
                "Check Settings > Editor > Live Templates > Kotlin > Compose.",

        "Use Interactive Preview Mode" to
                "Click the 'Interactive' button in the Compose Preview toolbar to interact with your UI directly — scrolling lists, pressing buttons, running animations — without leaving the IDE.",

        "Use Live Edit (Hot Reload)" to
                "Enable Live Edit in Android Studio (Giraffe/Flamingo+). Run your app, then edit composables and press Ctrl+' (Cmd+' on Mac) to apply changes instantly. " +
                "Great for UI tweaks; works best for modifier changes and simple updates.",

        "Use @PreviewParameter for multiple states" to
                "Annotate preview parameters with `@PreviewParameter` to automatically generate multiple preview variants. " +
                "Example: `@PreviewParameter(UserProfileProvider::class)` renders the composable for each sample user.",

        "Test with Compose Testing APIs" to
                "Use `createComposeRule()` for UI tests. Example:\n" +
                "```kotlin\n" +
                "composeTestRule.onNodeWithText(\"Continue\").assertIsDisplayed()\n" +
                "```\n" +
                "Also use `createAndroidComposeRule<MainActivity>()` for integration tests involving navigation and back stack.",

        "Leverage Third-Party Libraries" to
                "Boost development with:\n" +
                "- **Coil/Landscapist**: Image loading\n" +
                "- **Voyager/Decompose**: Navigation & MVI\n" +
                "- **Paparazzi/Shot**: Screenshot testing\n" +
                "- **Hilt/Koin**: DI integration\n" +
                "- **Orbit MVI**: Type-safe unidirectional flow",

        "Follow Unidirectional Data Flow" to
                "Adopt state hoisting: state flows down, events flow up. Composables should be stateless (except UI state). " +
                "Expose immutable `UiState` from ViewModel and handle events via callbacks. Makes UI predictable and testable.",

        "Compose is performant by default" to
                "But following best practices — stable keys, caching with `remember`, `derivedStateOf`, profiling, and clean architecture — " +
                "will help your app run smoothly, load faster, and scale beautifully across devices."
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorScheme.background)
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items(composeTips.entries.toList()) { (title, text) ->
            Spacer(modifier = Modifier.height(10.dp))
            CustomCard4Anime1(titel = title, text = text)
        }
    }
}

@Composable
fun CustomCard4Anime1(titel: String, text: String) {
    var isExpanded by remember { mutableStateOf(false) }
    val updateTranision = updateTransition(
        targetState = isExpanded,
        label = "Transition"
    )
    val height = updateTranision.animateDp(
        transitionSpec = {
            tween(
                durationMillis = 400
            )
        }
    ) {
        if (it) 150.dp else 40.dp
    }
    Card(
        modifier = Modifier
            .height(height.value)
            .width(300.dp)
    ) {
        Row {
            Text(
                titel,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f),
                textAlign = TextAlign.Center
            )
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = "ArrowDropDown",
                tint = colorScheme.primary,
                modifier = Modifier
                    .size(30.dp)
                    .weight(0.8f)
                    .clickable(
                        onClick = {
                            isExpanded = !isExpanded
                        }
                    )
            )
        }

        AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut(),
            label = ""
        ) {
            Text(
                text = text,
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun Anime2() {

}


@Preview(showBackground = true)
@Composable
fun AnimationPreview() {
    ArcTheme(darkTheme = true) {
        Anime1()
    }
}