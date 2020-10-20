package com.ayy.webview.autoservice.command

import android.content.ComponentName
import android.content.Intent
import com.ayy.base.BaseApplication
import com.ayy.common.Constant
import com.ayy.usercenter.event.LoginEvent
import com.ayy.webview.IMain2WebViewInterface
import com.google.auto.service.AutoService
import com.google.gson.Gson
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


@AutoService(ICommand::class)
class LoginCommand : ICommand {

    var callback: IMain2WebViewInterface? = null
    var callbackName = ""

    init {
        EventBus.getDefault().register(this)
    }


    override fun getCommandName(): String {
        return Constant.COMMAND_LOGIN
    }

    override fun executeCommand(params: String?, callback: IMain2WebViewInterface?) {
        val intent = Intent()
        intent.component =
            ComponentName(BaseApplication.context, "com.ayy.usercenter.LoginActivity")
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        BaseApplication.context.startActivity(intent)
        this.callback = callback
        val map = Gson().fromJson(params, Map::class.java)
        if (map != null) {
            this.callbackName = map["callbackName"].toString()
        }
    }

    @Subscribe
    fun onResult(loginEvent: LoginEvent) {
        //主进程到webview进程
        this.callback?.onResult(callbackName, loginEvent.username)
    }
}