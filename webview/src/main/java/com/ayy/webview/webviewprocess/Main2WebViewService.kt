package com.ayy.webview.webviewprocess

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.ayy.webview.IMain2WebViewInterface

class Main2WebViewService: Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return object : IMain2WebViewInterface.Stub() {
            override fun onResult(callbackName: String?, response: String?) {

            }

        }
    }
}