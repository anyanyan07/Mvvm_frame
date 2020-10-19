package com.ayy.webview.bean

import com.google.gson.JsonObject

data class JsParameter(
    var action:String?,
    var parameters: JsonObject
)