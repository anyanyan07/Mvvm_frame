package com.ayy.mvvm_frame

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ayy.base.autoservice.AutoServiceUtils
import com.ayy.common.Constant
import com.ayy.common.IWebViewService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun click(view: View) {
        AutoServiceUtils.load(IWebViewService::class.java)
            ?.startWebViewActivity(
                this,
//                Constant.ANDROID_ASSET_URI + "Demo.html",
                "http://ynyd.ynicity.cn:9080/flep/app/snapshot",
                showTitle = true
            )
    }
}