package com.ayy.wan_android.mvvm.model

import com.ayy.base.mvvm.BaseModel
import com.ayy.base.mvvm.BaseObserver
import com.ayy.base.mvvm.BaseViewModel
import com.ayy.base.mvvm.IBaseModelListener
import com.ayy.common.CacheKey
import com.ayy.base.BaseResponse
import com.ayy.wan_android.bean.TabData
import com.ayy.wan_android.mvvm.viewmodel.ItemViewModel
import com.ayy.wan_android.net.WanAndroid

class ArticleListModel(
    private var cid: Int,
    listener: IBaseModelListener<List<BaseViewModel>>
) : BaseModel<List<BaseViewModel>, BaseResponse<TabData>>(
    listener,
    true,
    0,
    cacheKey = CacheKey.ARTICLE_LIST
) {
    companion object {
        const val PRE_JSON =
            "{\"curPage\":1,\"datas\":[{\"apkLink\":\"\",\"audit\":1,\"author\":\"\",\"canEdit\":false,\"chapterId\":60,\"chapterName\":\"Android Studio相关\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"descMd\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":9652,\"link\":\"https://www.jianshu.com/p/6e41b83b5505\",\"niceDate\":\"2019-10-13 22:54\",\"niceShareDate\":\"2019-10-13 22:27\",\"origin\":\"\",\"prefix\":\"\",\"projectLink\":\"\",\"publishTime\":1570978470000,\"realSuperChapterId\":150,\"selfVisible\":0,\"shareDate\":1570976838000,\"shareUser\":\"鸿洋\",\"superChapterId\":60,\"superChapterName\":\"开发环境\",\"tags\":[],\"title\":\"Android Studio插件开发6--Settings创建配置页\",\"type\":0,\"userId\":2,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"audit\":1,\"author\":\"\",\"canEdit\":false,\"chapterId\":60,\"chapterName\":\"Android Studio相关\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"descMd\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":9651,\"link\":\"https://www.jianshu.com/p/16607caffb63\",\"niceDate\":\"2019-10-13 22:54\",\"niceShareDate\":\"2019-10-13 22:27\",\"origin\":\"\",\"prefix\":\"\",\"projectLink\":\"\",\"publishTime\":1570978464000,\"realSuperChapterId\":150,\"selfVisible\":0,\"shareDate\":1570976834000,\"shareUser\":\"鸿洋\",\"superChapterId\":60,\"superChapterName\":\"开发环境\",\"tags\":[],\"title\":\"Android Studio插件开发5--JBPopup创建\",\"type\":0,\"userId\":2,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"audit\":1,\"author\":\"\",\"canEdit\":false,\"chapterId\":60,\"chapterName\":\"Android Studio相关\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"descMd\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":9650,\"link\":\"https://www.jianshu.com/p/d55ca4dbc821\",\"niceDate\":\"2019-10-13 22:54\",\"niceShareDate\":\"2019-10-13 22:27\",\"origin\":\"\",\"prefix\":\"\",\"projectLink\":\"\",\"publishTime\":1570978457000,\"realSuperChapterId\":150,\"selfVisible\":0,\"shareDate\":1570976829000,\"shareUser\":\"鸿洋\",\"superChapterId\":60,\"superChapterName\":\"开发环境\",\"tags\":[],\"title\":\"Android Studio插件开发4--ToolWindow创建\",\"type\":0,\"userId\":2,\"visible\":1,\"zan\":0},{\"apkLink\":\"\",\"audit\":1,\"author\":\"\",\"canEdit\":false,\"chapterId\":60,\"chapterName\":\"Android Studio相关\",\"collect\":false,\"courseId\":13,\"desc\":\"\",\"descMd\":\"\",\"envelopePic\":\"\",\"fresh\":false,\"id\":9649,\"link\":\"https://www.jianshu.com/p/e4c0acbd6714\",\"niceDate\":\"2019-10-13 22:54\",\"niceShareDate\":\"2019-10-13 22:27\",\"origin\":\"\",\"prefix\":\"\",\"projectLink\":\"\",\"publishTime\":1570978450000,\"realSuperChapterId\":150,\"selfVisible\":0,\"shareDate\":1570976824000,\"shareUser\":\"鸿洋\",\"superChapterId\":60,\"superChapterName\":\"开发环境\",\"tags\":[],\"title\":\"Android Studio插件开发3--Dialog创建\",\"type\":0,\"userId\":2,\"visible\":1,\"zan\":0}],\"offset\":0,\"over\":false,\"pageCount\":3,\"size\":20,\"total\":44}"
    }

    override fun load() {
        WanAndroid.instance.getApi().getTabData(pageNum, cid)
            .compose(WanAndroid.instance.applyTransform())
            .subscribe(BaseObserver(this))
    }

    override fun getPredefinedJson(): String? {
        return null
    }

    override fun isNeedLoad(data: BaseResponse<TabData>?): Boolean {
        return true
    }

    override fun onSuccess(baseResponse: BaseResponse<TabData>?, isFromCache: Boolean) {
        baseResponse?.apply {
            if (isSuccess()) {
                val result = mutableListOf<ItemViewModel>()
                data?.datas?.forEach {
                    val model =
                        ItemViewModel()
                    model.title = it.title
                    model.desc = it.chapterName
                    model.link = it.link
                    result.add(model)
                }
                notifyLoadSuccess(
                    result.toList(), baseResponse, isFromCache, data?.curPage != data?.pageCount
                )
                return
            }
            notifyLoadFail(baseResponse.errorMsg ?: "response is not success", isFromCache)
        }
    }

    override fun onFail(throwable: Throwable, isFromCache: Boolean) {
        notifyLoadFail(throwable.message ?: "load fail", isFromCache)
    }
}