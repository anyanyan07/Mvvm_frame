package com.ayy.wan_android.mvvm.model

import com.ayy.base.mvvm.BaseModel
import com.ayy.base.mvvm.BaseObserver
import com.ayy.base.mvvm.IBaseModelListener
import com.ayy.common.CacheKey
import com.ayy.base.BaseResponse
import com.ayy.wan_android.bean.ArticleChannel
import com.ayy.wan_android.net.WanAndroid

class ArticleChannelModel(listener: IBaseModelListener<List<ArticleChannel>>) :
    BaseModel<List<ArticleChannel>, BaseResponse<List<ArticleChannel>>>(
        listener,
        false,
        cacheKey = CacheKey.ARTICLE_CHANNEL
    ) {

    companion object {
        const val PRE_JSON =
            "[{\"courseId\":13,\"id\":150,\"name\":\"开发环境\",\"order\":1,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1},{\"courseId\":13,\"id\":9,\"name\":\"四大组件\",\"order\":10,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1},{\"courseId\":13,\"id\":25,\"name\":\"常用控件\",\"order\":15,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1},{\"courseId\":13,\"id\":29,\"name\":\"用户交互\",\"order\":20,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1},{\"courseId\":13,\"id\":34,\"name\":\"网络访问\",\"order\":25,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1},{\"courseId\":13,\"id\":72,\"name\":\"图片加载\",\"order\":30,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1},{\"courseId\":13,\"id\":35,\"name\":\"数据存储\",\"order\":35,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1},{\"courseId\":13,\"id\":36,\"name\":\"动画效果\",\"order\":40,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1},{\"courseId\":13,\"id\":37,\"name\":\"自定义控件\",\"order\":45,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1},{\"courseId\":13,\"id\":38,\"name\":\"多媒体技术\",\"order\":50,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1},{\"courseId\":13,\"id\":39,\"name\":\"5.+高新技术\",\"order\":55,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":0},{\"courseId\":13,\"id\":53,\"name\":\"热门专题\",\"order\":60,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":0},{\"courseId\":13,\"id\":127,\"name\":\"硬件模块\",\"order\":70,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1},{\"courseId\":13,\"id\":128,\"name\":\"JNI\",\"order\":75,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1},{\"courseId\":13,\"id\":130,\"name\":\"常用SDK\",\"order\":85,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1},{\"courseId\":13,\"id\":152,\"name\":\"framework\",\"order\":90,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1},{\"courseId\":13,\"id\":156,\"name\":\"项目必备\",\"order\":95,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1},{\"courseId\":13,\"id\":244,\"name\":\"Java深入\",\"order\":120,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1},{\"courseId\":13,\"id\":253,\"name\":\"完整开源项目\",\"order\":130,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1},{\"courseId\":13,\"id\":532,\"name\":\"鸿蒙\",\"order\":250,\"parentChapterId\":0,\"userControlSetTop\":false,\"visible\":1}]"
    }

    override fun load() {
        WanAndroid.instance.getSystem()
            .subscribe(BaseObserver<BaseResponse<List<ArticleChannel>>>(this))
    }

    override fun getPredefinedJson(): String? {
        return null
    }

    override fun onFail(throwable: Throwable,isFromCache: Boolean) {
        notifyLoadFail(throwable.message ?: "load fail",isFromCache)
    }

    override fun isNeedLoad(data: BaseResponse<List<ArticleChannel>>?): Boolean {
        return false
    }

    override fun onSuccess(
        baseResponse: BaseResponse<List<ArticleChannel>>?,
        isFromCache: Boolean
    ) {
        baseResponse?.apply {
            if (isSuccess()) {
                notifyLoadSuccess(data, baseResponse, isFromCache)
                return
            }
        }
        notifyLoadFail(baseResponse?.errorMsg ?: "response is not success",isFromCache)
    }
}