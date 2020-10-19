package com.ayy.webview.webviewprocess.webviewclient

interface WebViewCallback {
    fun onPageStarted(url: String)
    fun onPageFinished(url: String)
    fun onError()
    fun updateTitle(title:String?)
}