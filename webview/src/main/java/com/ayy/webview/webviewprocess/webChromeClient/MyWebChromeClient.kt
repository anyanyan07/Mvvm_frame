package com.ayy.webview.webviewprocess.webChromeClient

import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.ayy.webview.webviewprocess.webviewclient.WebViewCallback

class MyWebChromeClient(var webViewCallback: WebViewCallback) : WebChromeClient() {

    companion object {
        private const val TAG = "MyWebChromeClient"
    }

    override fun onReceivedTitle(view: WebView?, title: String?) {
        super.onReceivedTitle(view, title)
        webViewCallback.updateTitle(title)
    }

    override fun onConsoleMessage(message: ConsoleMessage?): Boolean {
        Log.d(TAG, "onConsoleMessage: ${message?.message()}")
        return super.onConsoleMessage(message)
    }
}