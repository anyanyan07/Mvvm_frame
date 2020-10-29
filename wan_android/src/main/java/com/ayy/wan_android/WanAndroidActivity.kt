package com.ayy.wan_android

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ayy.base.BaseResponse
import com.ayy.base.loadsir.ErrorCallback
import com.ayy.base.loadsir.LoadingCallback
import com.ayy.base.mvvm.IBaseModelListener
import com.ayy.base.mvvm.PageResult
import com.ayy.common.CacheKey
import com.ayy.wan_android.adapter.ViewPagerAdapter
import com.ayy.wan_android.bean.ArticleChannel
import com.ayy.wan_android.databinding.ActivityWanAndroidBinding
import com.ayy.wan_android.mvvm.model.ArticleChannelModel
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

class WanAndroidActivity : AppCompatActivity(), IBaseModelListener<List<ArticleChannel>> {
    private lateinit var mBinding: ActivityWanAndroidBinding
    private lateinit var model: ArticleChannelModel
    private lateinit var loadService: LoadService<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_wan_android)
// todo：有点问题
//        loadService = LoadSir.getDefault().register(mBinding.root) {
//            loadService.showCallback(LoadingCallback::class.java)
//            model.getCachedAndLoad()
//        }
        //延时请求
        mBinding.tabs.postDelayed(object : Runnable {
            override fun run() {
                model = ArticleChannelModel(this@WanAndroidActivity)
                model.getCachedAndLoad()
            }
        }, 500)
    }

    private fun initViewPager(tabs: List<ArticleChannel>?) {
        tabs?.apply {
            mBinding.viewPager.adapter =
                ViewPagerAdapter(
                    supportFragmentManager,
                    this
                )
            mBinding.tabs.setupWithViewPager(mBinding.viewPager)
        }
    }

    override fun onLoadFail(message: String, isFromCache: Boolean, page: PageResult?) {
        if (isFromCache) {
//            loadService.showCallback(ErrorCallback::class.java)
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onLoadSuccess(
        data: List<ArticleChannel>?,
        isFromCache: Boolean,
        page: PageResult?
    ) {
//        loadService.showSuccess()
        initViewPager(data)
    }
}