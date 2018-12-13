package com.example.gl.demo_android.paging.pagekeydatasource

class News(var date: String = "",
           var stories: List<StoriesBean> = emptyList()) {

    class StoriesBean {
        var type: Int = 0
        var id: Int = 0
        var ga_prefix: String? = null
        var title: String? = null
        var images: List<String>? = null
    }
}