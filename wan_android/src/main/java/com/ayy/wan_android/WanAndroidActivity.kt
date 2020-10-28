package com.ayy.wan_android

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ayy.base.loadsir.ErrorCallback
import com.ayy.base.loadsir.LoadingCallback
import com.ayy.base.mvvm.IBaseModelListener
import com.ayy.base.mvvm.PageResult
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
        loadService = LoadSir.getDefault().register(mBinding.root) {
            loadService.showCallback(LoadingCallback::class.java)
            model.load()
        }
        model = ArticleChannelModel(this)
        model.load()
    }

    private fun initViewPager(tabs: List<ArticleChannel>) {
        mBinding.viewPager.adapter =
            ViewPagerAdapter(
                supportFragmentManager,
                tabs
            )
        mBinding.tabs.setupWithViewPager(mBinding.viewPager)
    }

    override fun onLoadFail(message: String, page: PageResult?) {
        loadService.showCallback(ErrorCallback::class.java)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onLoadSuccess(data: List<ArticleChannel>, page: PageResult?) {
        loadService.showSuccess()
        initViewPager(data)
    }
}