package com.ayy.wan_android.net

import com.ayy.network.Http
import com.ayy.wan_android.BuildConfig
import com.ayy.wan_android.base.BaseResponse
import com.ayy.wan_android.bean.ArticleChannel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.schedulers.Schedulers

class WanAndroid private constructor() {
    private var apiService: WanAndroidApi

    companion object {
        val instance = WanAndroid()
    }

    init {
        apiService =
            Http.getInstance(BuildConfig.BASE_URL).getApiService(WanAndroidApi::class.java)
    }

    public fun getApi(): WanAndroidApi {
        return apiService
    }

    public fun getSystem(): Observable<BaseResponse<ArrayList<ArticleChannel>>> {
        return apiService.getSystem()
            .compose(applyTransform())
    }

    public fun <T> applyTransform(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }


}