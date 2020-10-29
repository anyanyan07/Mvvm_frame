package com.ayy.base.mvvm

import android.text.TextUtils
import com.ayy.base.utils.CacheUtils
import com.ayy.base.utils.GenericUtils
import com.google.gson.Gson
import java.lang.ref.WeakReference

abstract class BaseModel<R, N> : Callback<N> {
    private var listenerReference: WeakReference<IBaseModelListener<R>>
    protected var pageNum: Int = -1
    private val isPaging: Boolean
    private val INIT_PAGE_NUM: Int
    private var isLoading = false
    private var cacheKey: String? = null

    constructor(
        listener: IBaseModelListener<R>,
        isPaging: Boolean,
        initPageNum: Int = 0,
        cacheKey: String? = null
    ) {
        listenerReference = WeakReference(listener)
        this.isPaging = isPaging
        INIT_PAGE_NUM = initPageNum
        pageNum = initPageNum
        this.cacheKey = cacheKey
    }

    abstract fun load()

    abstract fun getPredefinedJson(): String?

    abstract fun isNeedLoad(data: N?): Boolean

    fun refresh() {
        if (!isLoading) {
            pageNum = INIT_PAGE_NUM
            load()
            isLoading = true
        }
    }

    /**
     * 先取缓存数据--再取预制数据--再取网络数据
     */
    fun getCachedAndLoad() {
        //需要缓存
        if (isLoading) {
            return
        }
        isLoading = true
        if (!TextUtils.isEmpty(cacheKey)) {
            val cachedValue = CacheUtils.getString(cacheKey!!)
            if (!TextUtils.isEmpty(cachedValue)) {
                val netData =
                    Gson().fromJson<N>(cachedValue, GenericUtils.getGenericType(this))
                onSuccess(netData, true)
                if (isNeedLoad(netData)) {
                    load()
                }
                return
            }
            //取不到缓存,取预制数据
            val predefinedJson = getPredefinedJson()
            if (!TextUtils.isEmpty(predefinedJson)) {
                val preData =
                    Gson().fromJson<N>(predefinedJson, GenericUtils.getGenericType(this))
                onSuccess(preData, true)
            }
            load()
        }
    }

    fun notifyLoadSuccess(
        result: R?,
        netData: N,
        isFromCache: Boolean,
        hasNextPage: Boolean = false
    ) {
        isLoading = false
        saveToCache(isFromCache, netData)
        if (isPaging) {
            listenerReference.get()
                ?.onLoadSuccess(
                    result,
                    isFromCache,
                    PageResult(pageNum == INIT_PAGE_NUM, hasNextPage)
                )
            if (!isFromCache) {
                pageNum++
            }
        } else {
            listenerReference.get()?.onLoadSuccess(result, isFromCache)
        }
    }

    private fun saveToCache(isFromCache: Boolean, netData: N) {
        if (isFromCache) {
            return
        }
        if (isPaging && (!TextUtils.isEmpty(cacheKey) && pageNum == INIT_PAGE_NUM)) {
            //保存第一页数据
            CacheUtils.putString(cacheKey!!, Gson().toJson(netData))
            return
        }
        if (!isPaging) {
            //非分页，保存全量数据
            CacheUtils.putString(cacheKey!!, Gson().toJson(netData))
        }
    }

    fun notifyLoadFail(message: String,isFromCache: Boolean) {
        isLoading = false
        if (isPaging) {
            listenerReference.get()
                ?.onLoadFail(message,isFromCache, PageResult(pageNum == INIT_PAGE_NUM, true))
        }
        listenerReference.get()?.onLoadFail(message,isFromCache)
    }
}