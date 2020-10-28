package com.ayy.base.mvvm

interface IBaseModelListener<DATA> {
    fun onLoadSuccess(data: DATA, page: PageResult? = null)
    fun onLoadFail(message: String, page: PageResult? = null)
}