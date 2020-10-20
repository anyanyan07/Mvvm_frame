package com.ayy.base

import android.app.Application
import android.content.Context
import com.ayy.base.loadsir.EmptyCallback
import com.ayy.base.loadsir.ErrorCallback
import com.ayy.base.loadsir.LoadingCallback
import com.kingja.loadsir.core.LoadSir
import org.greenrobot.eventbus.EventBus


open class BaseApplication : Application() {

    companion object {
        lateinit var context: Context
        fun getInstance(): Context {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        LoadSir.beginBuilder()
            .addCallback(EmptyCallback())
            .addCallback(LoadingCallback())
            .addCallback(ErrorCallback())
            .setDefaultCallback(LoadingCallback::class.java) //设置默认状态页
            .commit()
//        EventBus.builder().addIndex(MyEventBusIndex()).installDefaultEventBus()
    }
}