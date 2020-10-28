package com.ayy.wan_android.mvvm

import android.content.Context
import android.view.View
import com.ayy.base.autoservice.AutoServiceUtils
import com.ayy.base.views.BaseCustomView
import com.ayy.common.IWebViewService
import com.ayy.wan_android.R
import com.ayy.wan_android.databinding.ItemBinding
import com.ayy.wan_android.mvvm.viewmodel.ItemViewModel

class ItemView(context: Context?) : BaseCustomView<ItemBinding, ItemViewModel>(context) {

    override fun getLayoutId(): Int {
        return R.layout.item
    }

    override fun onRootClicked(view: View) {
        AutoServiceUtils.load(IWebViewService::class.java)
            ?.startWebViewActivity(context, viewModel?.link ?: "")
    }

    override fun setDataToView(viewModel: ItemViewModel) {
        mBinding.viewModel = viewModel
    }
}