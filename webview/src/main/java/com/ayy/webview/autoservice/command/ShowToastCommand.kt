package com.ayy.webview.autoservice.command

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.ayy.base.BaseApplication
import com.ayy.common.Constant
import com.ayy.webview.IMain2WebViewInterface
import com.google.auto.service.AutoService
import com.google.gson.Gson

@AutoService(ICommand::class)
class ShowToastCommand : ICommand {
    override fun getCommandName(): String {
        return Constant.COMMAND_SHOW_TOAST
    }

    override fun executeCommand(params: String?,callback: IMain2WebViewInterface?) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            Toast.makeText(
                BaseApplication.context,
                Gson().fromJson(params, Map::class.java).get("message").toString(),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}