package com.ayy.webview.autoservice.command

import android.content.ComponentName
import android.content.Intent
import com.ayy.base.BaseApplication
import com.ayy.common.Constant
import com.ayy.webview.IMain2WebViewInterface
import com.google.auto.service.AutoService
import com.google.gson.Gson

@AutoService(ICommand::class)
class OpenPageCommand : ICommand {
    override fun getCommandName(): String {
        return Constant.COMMAND_OPEN_PAGE
    }

    override fun executeCommand(params: String?,callback:IMain2WebViewInterface?) {
        var map = Gson().fromJson(params, Map::class.java)
        val intent = Intent()
        intent.component =
            ComponentName(BaseApplication.context, map["target"].toString())
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        BaseApplication.context.startActivity(intent)
    }
}