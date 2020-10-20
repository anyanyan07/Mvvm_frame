package com.ayy.webview.autoservice.command

import com.ayy.webview.IMain2WebViewInterface

interface ICommand {
    fun getCommandName(): String

    fun executeCommand(params: String?,callback: IMain2WebViewInterface? = null)
}