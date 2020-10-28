package com.ayy.wan_android

import android.app.Application
import android.content.Context
import android.content.Intent
import com.ayy.common.IWanAndroidService
import com.google.auto.service.AutoService

@AutoService(IWanAndroidService::class)
class WanAndroidServiceImpl : IWanAndroidService {
    override fun startWanAndroid(context: Context) {
        val intent = Intent(context, WanAndroidActivity::class.java)
        if (context is Application) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}