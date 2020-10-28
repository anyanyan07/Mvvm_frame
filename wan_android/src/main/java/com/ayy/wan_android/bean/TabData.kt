package com.ayy.wan_android.bean

data class TabData(
    var curPage: Int,
    var pageCount:Int,
    var datas: ArrayList<Item>
)

data class Item(
    var author: String?,
    var chapterId: Int,
    var chapterName: String,
    var courseId: Int,
    var id: Int,
    var link: String?,
    var title: String
)