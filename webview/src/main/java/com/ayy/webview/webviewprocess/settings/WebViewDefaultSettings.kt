package com.ayy.webview.webviewprocess.settings

import android.webkit.WebView

class WebViewDefaultSettings {
    companion object{
        val instance = WebViewDefaultSettings()
    }

    fun setSettings(webView:WebView){
        val settings = webView.settings
        settings.javaScriptEnabled = true

    }
}