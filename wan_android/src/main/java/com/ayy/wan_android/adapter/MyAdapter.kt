package com.ayy.wan_android.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ayy.base.mvvm.BaseViewModel
import com.ayy.base.recyclerview.BaseViewHolder
import com.ayy.wan_android.mvvm.ItemView

class MyAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    var data: List<BaseViewModel>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(ItemView(parent.context))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        data?.let {
            val itemView = holder.customView
            if (position < it.size) {
                itemView.bindData(it.get(position))
            }
        }
    }
}