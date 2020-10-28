package com.ayy.wan_android.mvvm.model

import com.ayy.base.mvvm.BaseModel
import com.ayy.base.mvvm.BaseViewModel
import com.ayy.base.mvvm.IBaseModelListener
import com.ayy.wan_android.mvvm.viewmodel.ItemViewModel
import com.ayy.wan_android.net.WanAndroid

class ArticleListModel(
    private var cid: Int,
    listener: IBaseModelListener<List<BaseViewModel>>
) : BaseModel<List<BaseViewModel>>(listener, true, 0) {


    override fun load() {
        WanAndroid.instance.getApi().getTabData(pageNum, cid)
            .compose(WanAndroid.instance.applyTransform())
            .subscribe({ baseResponse ->
                if (baseResponse.isSuccess()) {
                    val data = mutableListOf<ItemViewModel>()
                    baseResponse.data?.datas?.forEach {
                        val model =
                            ItemViewModel()
                        model.title = it.title
                        model.desc = it.chapterName
                        model.link = it.link
                        data.add(model)
                    }
                    notifyLoadSuccess(
                        data.toList(),
                        baseResponse.data?.curPage != baseResponse.data?.pageCount
                    )
                } else {
                    notifyLoadFail(baseResponse.errorMsg ?: "response is not success")
                }
            }, { throwable ->
                notifyLoadFail(throwable.message ?: "load fail")
            })
    }
}