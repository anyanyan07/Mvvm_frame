package com.ayy.webview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ayy.webview.databinding.ActivityWebViewBinding
import com.ayy.webview.util.Constants

class WebViewActivity : AppCompatActivity(), WebViewFragment.IWebViewCloseService {

    private lateinit var mBinding: ActivityWebViewBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web_view)
        loadWebView()
    }

    private fun loadWebView() {
        val url = intent.getStringExtra(Constants.URL)
        val title = intent.getStringExtra(Constants.TITLE)
        val showTitle = intent.getBooleanExtra(Constants.SHOW_TITLE, true)
        val beginTransaction = supportFragmentManager.beginTransaction()
        val webViewFragment = WebViewFragment.newInstance(url, title, showTitle)
        webViewFragment.webViewCloseService = this
        beginTransaction.replace(R.id.web_view_frame, webViewFragment)
        beginTransaction.commit()
    }

    override fun close() {
        finish()
    }
}