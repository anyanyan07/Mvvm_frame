package com.ayy.webview.mainprocess

import android.app.Service
import android.content.Intent
import android.os.IBinder

class WebViewService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return WebViewStub()
    }
}
