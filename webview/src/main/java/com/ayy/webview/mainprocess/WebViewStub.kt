package com.ayy.webview.mainprocess

import com.ayy.webview.autoservice.command.ICommand
import com.ayy.webview.IMain2WebViewInterface
import com.ayy.webview.IWebView2MainInterface
import java.util.*

private const val TAG = "WebViewStub"

class WebViewStub : IWebView2MainInterface.Stub() {
    private var commands: MutableMap<String, ICommand> = mutableMapOf()

    init {
        val iterator = ServiceLoader.load(ICommand::class.java).iterator()
        while (iterator.hasNext()) {
            val command = iterator.next()
            if (command != null && !commands.containsKey(command.getCommandName())) {
                commands[command.getCommandName()] = command
            }
        }
    }


    override fun handleCommand(commandName: String?, params: String?, callback: IMain2WebViewInterface?) {
        commands[commandName]?.executeCommand(params,callback)
    }
}