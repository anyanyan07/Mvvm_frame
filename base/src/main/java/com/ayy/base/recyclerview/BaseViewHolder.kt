package com.ayy.base.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ayy.base.mvvm.BaseViewModel
import com.ayy.base.views.IBaseCustomView

class BaseViewHolder(var customView: IBaseCustomView<BaseViewModel>) :
    RecyclerView.ViewHolder((customView as View)) {

    fun bind(viewModel: BaseViewModel) {
        customView.bindData(viewModel)
    }
}