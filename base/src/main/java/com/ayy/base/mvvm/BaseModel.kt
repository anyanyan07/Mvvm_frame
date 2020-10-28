package com.ayy.base.mvvm

import java.lang.ref.WeakReference

abstract class BaseModel<R> {
    private var listenerReference: WeakReference<IBaseModelListener<R>>
    protected var pageNum: Int = -1
    private val isPaging: Boolean
    private val INIT_PAGE_NUM: Int
    private var isLoading = false

    constructor(listener: IBaseModelListener<R>, isPaging: Boolean, initPageNum: Int = 0) {
        listenerReference = WeakReference(listener)
        this.isPaging = isPaging
        INIT_PAGE_NUM = initPageNum
        pageNum = initPageNum
    }

    public fun refresh() {
        if (!isLoading) {
            pageNum = INIT_PAGE_NUM
            load()
            isLoading = true
        }
    }

    abstract fun load()

    fun notifyLoadSuccess(result: R, hasNextPage: Boolean = false) {
        isLoading = false
        if (isPaging) {
            listenerReference.get()
                ?.onLoadSuccess(result, PageResult(pageNum == INIT_PAGE_NUM, hasNextPage))
            pageNum++
        } else {
            listenerReference.get()?.onLoadSuccess(result)
        }
    }

    fun notifyLoadFail(message: String) {
        isLoading = false
        if (isPaging) {
            listenerReference.get()?.onLoadFail(message, PageResult(pageNum == INIT_PAGE_NUM, true))
        }
        listenerReference.get()?.onLoadFail(message)
    }
}