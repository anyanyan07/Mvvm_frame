package com.ayy.wan_android.bean

data class ArticleChannel(
    var courseId: Int,
    var id: Int,
    var name: String?,
    var order: Int,
    var parentChapterId: Int,
    var userControlSetTop: Boolean,
    var visible: Int,
    var children: ArrayList<ArticleChannel>?
)