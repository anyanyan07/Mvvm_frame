package com.ayy.webview.mainprocess

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.ayy.base.BaseApplication.Companion.context
import com.ayy.webview.IMain2WebViewInterface
import com.ayy.webview.IWebView2MainInterface
import com.ayy.webview.webviewprocess.BaseWebView

class WebViewProcessCommandDispatcher : ServiceConnection {
    private var webView2MainService: IWebView2MainInterface? = null

    companion object {
        val Instance = WebViewProcessCommandDispatcher()
    }

    fun initAidl() {
        val intent = Intent(context, WebViewService::class.java)
        context.bindService(intent, this, Context.BIND_AUTO_CREATE)
    }

    override fun onServiceDisconnected(p0: ComponentName?) {
        webView2MainService = null
    }

    override fun onServiceConnected(p0: ComponentName?, iBinder: IBinder?) {
        webView2MainService = IWebView2MainInterface.Stub.asInterface(iBinder)
    }

    fun dispatch(commandName: String?, params: String?, webView: BaseWebView) {
        webView2MainService?.handleCommand(commandName, params, object : IMain2WebViewInterface.Stub() {
            override fun onResult(callbackName: String?, response: String?) {
                webView.handleCallback(callbackName, response)
            }

        })
    }
}