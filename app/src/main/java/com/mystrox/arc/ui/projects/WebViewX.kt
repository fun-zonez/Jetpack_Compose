package com.mystrox.arc.ui.projects

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun WebViewX(murl:String = "https://www.google.com/"){

//    var murl = remember { mutableStateOf("") }
//    Column {

//        OutlinedTextField(
//            value = murl.value,
//            onValueChange = {
//                murl.value = "https://www.google.com/search?q=$it"
//            },
//            trailingIcon = {
//
//                IconButton(onClick = {
//                    murl.value = ""
//                }) {
//                    Icons.Default.Clear
//                }
//                           },
//            modifier = Modifier.background(Color.Black)
//                .fillMaxWidth(),
//            keyboardOptions = KeyboardOptions(
//                imeAction = ImeAction.Search
//            ),
//            keyboardActions = KeyboardActions(
//                onSearch = {
//
//                }
//            )
//
//        )
//
//    }
        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    webViewClient = WebViewClient()
                    loadUrl(murl)
                }
            },
            update = {
                it.loadUrl(murl)
            },
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxSize()
        )

}

@Preview
@Composable
fun WebPreview(){
    WebViewX()
}