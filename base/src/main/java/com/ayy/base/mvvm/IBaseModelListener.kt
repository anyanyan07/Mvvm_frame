package com.ayy.base.mvvm

interface IBaseModelListener<DATA> {
    fun onLoadSuccess(data: DATA?, isFromCache: Boolean, page: PageResult? = null)
    fun onLoadFail(message: String,isFromCache: Boolean, page: PageResult? = null)
}