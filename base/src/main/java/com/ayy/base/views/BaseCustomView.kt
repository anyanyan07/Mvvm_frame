package com.ayy.base.views

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ayy.base.mvvm.BaseViewModel

abstract class BaseCustomView<B : ViewDataBinding, VM : BaseViewModel> : LinearLayout,
    IBaseCustomView<VM> {
    var mBinding: B
    var viewModel: VM? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context), getLayoutId()
            , this, false
        )
        mBinding.root.setOnClickListener {
            onRootClicked(it)
        }
        addView(mBinding.root)
    }

    override fun bindData(viewModel: VM) {
        setDataToView(viewModel)
        mBinding.executePendingBindings()
        this.viewModel = viewModel
    }

    abstract fun getLayoutId(): Int
    abstract fun onRootClicked(view: View)
    abstract fun setDataToView(viewModel: VM)
}