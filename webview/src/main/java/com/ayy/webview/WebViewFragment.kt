package com.ayy.webview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ayy.base.loadsir.ErrorCallback
import com.ayy.base.loadsir.LoadingCallback
import com.ayy.webview.databinding.FragmentWebViewBinding
import com.ayy.webview.webviewprocess.settings.WebViewDefaultSettings
import com.ayy.webview.util.Constants
import com.ayy.webview.webviewprocess.webChromeClient.MyWebChromeClient
import com.ayy.webview.webviewprocess.webviewclient.MyWebViewClient
import com.ayy.webview.webviewprocess.webviewclient.WebViewCallback
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshListener


class WebViewFragment : Fragment(), WebViewCallback, OnRefreshListener {
    private lateinit var mBinding: FragmentWebViewBinding
    private var url: String? = null
    private var title: String? = null
    private var showTitle = true
    private var canNativeRefresh = true
    private var isError = false
    public var webViewCloseService: IWebViewCloseService? = null
    private var loadService: LoadService<Any>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            url = it.getString(Constants.URL)
            title = it.getString(Constants.TITLE)
            showTitle = it.getBoolean(Constants.SHOW_TITLE)
            canNativeRefresh = it.getBoolean(Constants.CAN_NATIVE_REFRESH)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.fragment_web_view,
            container,
            false
        )
        initTitle()
        mBinding.webView.setWebViewCallback(this)
        mBinding.refreshLayout.setEnableLoadMore(false)
        mBinding.refreshLayout.setEnableRefresh(canNativeRefresh)
        mBinding.refreshLayout.setOnRefreshListener(this)
        loadService =
            LoadSir.getDefault()
                .register(mBinding.refreshLayout, object : Callback.OnReloadListener {
                    override fun onReload(v: View?) {
                        loadService?.showCallback(LoadingCallback::class.java)
                        mBinding.webView.reload()
                    }
                })
        mBinding.webView.loadUrl(url ?: "https://www.baidu.com")
        return mBinding.root
    }

    private fun initTitle() {
        if (showTitle) {
            mBinding.titleInclude.titleLayout.visibility = View.VISIBLE
            mBinding.titleInclude.tvTitle.text = title ?: ""
            mBinding.titleInclude.ivBack.setOnClickListener {
                if (mBinding.webView.canGoBack()) {
                    mBinding.webView.goBack()
                } else {
                    //上层处理
                    webViewCloseService?.close()
                }
            }
        } else {
            mBinding.titleInclude.titleLayout.visibility = View.GONE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            url: String?,
            title: String? = null,
            showTitle: Boolean = true,
            canNativeRefresh: Boolean = true
        ) =
            WebViewFragment().apply {
                arguments = Bundle().apply {
                    putString(Constants.URL, url)
                    putString(Constants.TITLE, title)
                    putBoolean(Constants.SHOW_TITLE, showTitle)
                    putBoolean(Constants.CAN_NATIVE_REFRESH, canNativeRefresh)
                }
            }
    }

    interface IWebViewCloseService {
        fun close()
    }

    override fun onPageStarted(url: String) {
        loadService?.showCallback(LoadingCallback::class.java)
    }

    override fun onPageFinished(url: String) {
        if (isError) {
            loadService?.showCallback(ErrorCallback::class.java)
            isError = false
        } else {
            loadService?.showSuccess()
        }
        mBinding.refreshLayout.finishRefresh()
    }

    override fun onError() {
        isError = true
    }

    override fun updateTitle(title: String?) {
        mBinding.titleInclude.tvTitle.text = title
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        mBinding.webView.reload()
    }
}