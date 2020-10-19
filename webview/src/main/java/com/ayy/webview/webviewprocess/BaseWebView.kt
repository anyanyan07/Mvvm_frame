package com.ayy.webview.webviewprocess

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import com.ayy.webview.bean.JsParameter
import com.ayy.webview.webviewprocess.settings.WebViewDefaultSettings
import com.ayy.webview.webviewprocess.webChromeClient.MyWebChromeClient
import com.ayy.webview.webviewprocess.webviewclient.MyWebViewClient
import com.ayy.webview.webviewprocess.webviewclient.WebViewCallback
import com.google.gson.Gson

private const val TAG = "BaseWebView"

class BaseWebView : WebView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        WebViewDefaultSettings.instance.setSettings(this)
        addJavascriptInterface(this, "android")
    }

    fun setWebViewCallback(webViewCallback: WebViewCallback) {
        this.webViewClient = MyWebViewClient(webViewCallback)
        this.webChromeClient = MyWebChromeClient(webViewCallback)
    }

    /**
     * ios只接收一个参数，所以统一传参json
     */
    @JavascriptInterface
    fun takeNativeAction(json: String?) {
        if (TextUtils.isEmpty(json)) {
            Log.e(TAG, "takeNativeAction: js parameter is empty!!!")
            return
        }
        var jsParameter = Gson().fromJson(json, JsParameter::class.java)
        when (jsParameter.action) {
            "showToast" -> {
                Toast.makeText(
                    context,
                    jsParameter.parameters.get("message").asString,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {
            }
        }
    }
}