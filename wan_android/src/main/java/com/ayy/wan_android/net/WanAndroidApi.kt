package com.ayy.wan_android.net

import com.ayy.wan_android.base.BaseResponse
import com.ayy.wan_android.bean.ArticleChannel
import com.ayy.wan_android.bean.TabData
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WanAndroidApi {

    @GET("tree/json")
    fun getSystem(): Observable<BaseResponse<ArrayList<ArticleChannel>>>

    @GET("article/list/{pageNum}/json")
    fun getTabData(
        @Path("pageNum") pageNum: Int,
        @Query("cid") cid: Int
    ): Observable<BaseResponse<TabData>>
}