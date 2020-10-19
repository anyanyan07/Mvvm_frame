package com.ayy.common

import android.content.Context

interface IWebViewService {
    fun startWebViewActivity(
        context: Context,
        url: String,
        title: String = "",
        showTitle: Boolean = true,
        canNativeRefresh: Boolean = true
    )
}