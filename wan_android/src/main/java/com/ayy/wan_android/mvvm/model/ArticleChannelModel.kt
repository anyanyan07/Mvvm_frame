package com.ayy.wan_android.mvvm.model

import com.ayy.base.mvvm.BaseModel
import com.ayy.base.mvvm.IBaseModelListener
import com.ayy.wan_android.bean.ArticleChannel
import com.ayy.wan_android.net.WanAndroid

class ArticleChannelModel(listener: IBaseModelListener<List<ArticleChannel>>) :
    BaseModel<List<ArticleChannel>>(listener, false) {

    override fun load() {
        WanAndroid.instance.getSystem()
            .subscribe({ baseResponse ->
                if (baseResponse.isSuccess()) {
                    baseResponse.data?.let {
                        notifyLoadSuccess(it)
                        return@subscribe
                    }
                    notifyLoadFail("data is null")
                }
                notifyLoadFail(baseResponse.errorMsg ?: "response is not success")
            }, { throwable ->
                notifyLoadFail(throwable.message ?: "load fail")
            })
    }
}