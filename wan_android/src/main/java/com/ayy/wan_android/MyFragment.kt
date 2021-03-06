package com.ayy.wan_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ayy.base.BaseApplication
import com.ayy.base.loadsir.ErrorCallback
import com.ayy.base.loadsir.LoadingCallback
import com.ayy.base.mvvm.BaseViewModel
import com.ayy.base.mvvm.IBaseModelListener
import com.ayy.base.mvvm.PageResult
import com.ayy.wan_android.adapter.MyAdapter
import com.ayy.wan_android.databinding.FragmentMyBinding
import com.ayy.wan_android.mvvm.model.ArticleListModel
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

class MyFragment : Fragment(), IBaseModelListener<List<BaseViewModel>>, OnRefreshLoadMoreListener {

    private var tabId: Int? = null
    private lateinit var mBinding: FragmentMyBinding
    private lateinit var adapter: MyAdapter
    private lateinit var model: ArticleListModel
    private lateinit var viewModels: MutableList<BaseViewModel>
    private lateinit var loadService: LoadService<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tabId = arguments?.getInt(TAB_ID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my, container, false)
        mBinding.refreshLayout.setEnableLoadMore(true)
        mBinding.refreshLayout.setOnRefreshLoadMoreListener(this)
        viewModels = mutableListOf()
        adapter = MyAdapter()
        mBinding.rv.adapter = adapter
        model = ArticleListModel(tabId ?: -1, this)
//        loadService = LoadSir.getDefault()
//            .register(mBinding.refreshLayout) {
//                loadService.showCallback(LoadingCallback::class.java)
//                model.getCachedAndLoad()
//            }
        model.getCachedAndLoad()
//        return loadService.loadLayout
        return mBinding.root
    }

    companion object {
        const val TAB_ID = "tabId"
        fun newInstance(tabId: Int): MyFragment {
            val myFragment = MyFragment()
            val arguments = Bundle()
            arguments.putInt(TAB_ID, tabId)
            myFragment.arguments = arguments
            return myFragment
        }
    }

    override fun onLoadFail(message: String, isFromCache: Boolean, page: PageResult?) {
        if (isFromCache && page?.isFirstPage == true) {
//            loadService.showCallback(ErrorCallback::class.java)
        }
        Toast.makeText(BaseApplication.context, message, Toast.LENGTH_SHORT).show()
        mBinding.refreshLayout.finishRefresh()
        mBinding.refreshLayout.finishLoadMore()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        model.load()
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        model.refresh()
    }

    override fun onLoadSuccess(
        data: List<BaseViewModel>?,
        isFromCache: Boolean,
        page: PageResult?
    ) {
//        loadService.showSuccess()
        mBinding.refreshLayout.finishRefresh()
        mBinding.refreshLayout.finishLoadMore()
        if(page?.isFirstPage==true){
            viewModels.clear()
        }
        data?.apply {
            viewModels.addAll(this)
        }
        adapter.data = viewModels
    }

}
