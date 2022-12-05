package com.applydigital.petersfirstkotlinproject.pages

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun InfoWebView() {
    val url = "https://firtman.github.io/coffeemasters/webapp"

    AndroidView(factory = { context ->
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }, update = { webView ->
        webView.loadUrl(url)
    })
}

@Composable
fun InfoPage() {
    InfoWebView()
}