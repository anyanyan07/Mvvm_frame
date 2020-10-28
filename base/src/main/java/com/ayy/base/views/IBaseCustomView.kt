package com.ayy.base.views

import com.ayy.base.mvvm.BaseViewModel

interface IBaseCustomView<out VM : BaseViewModel> {
    fun bindData(viewModel: @UnsafeVariance VM)
}