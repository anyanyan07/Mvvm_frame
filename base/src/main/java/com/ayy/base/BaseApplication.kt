package com.ayy.base

import android.app.Application
import android.content.Context
import com.ayy.base.loadsir.EmptyCallback
import com.ayy.base.loadsir.ErrorCallback
import com.ayy.base.loadsir.LoadingCallback
import com.kingja.loadsir.core.LoadSir
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout


open class BaseApplication : Application() {

    companion object {
        init {
            //设置全局的Header构建器
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
                layout.setPrimaryColorsId(android.R.color.holo_blue_bright, android.R.color.white)
                ClassicsHeader(Companion.context)
            }
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
                ClassicsFooter(
                    context
                ).setDrawableSize(20f)
            }
        }

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