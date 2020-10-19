package com.ayy.webview

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.annotation.NonNull
import com.ayy.common.IWebViewService
import com.ayy.webview.util.Constants
import com.google.auto.service.AutoService

@AutoService(IWebViewService::class)
class WebViewServiceImpl : IWebViewService {
    override fun startWebViewActivity(
        @NonNull context: Context,
        @NonNull url: String,
        title: String,
        showTitle: Boolean,
        canNativeRefresh: Boolean
    ) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra(Constants.URL, url)
        intent.putExtra(Constants.TITLE, title)
        intent.putExtra(Constants.SHOW_TITLE, showTitle)
        intent.putExtra(Constants.CAN_NATIVE_REFRESH, canNativeRefresh)
        if (context is Application) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}