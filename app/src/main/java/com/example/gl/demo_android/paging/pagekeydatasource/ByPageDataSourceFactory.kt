package com.example.gl.demo_android.paging.pagekeydatasource

import android.arch.paging.DataSource

class ByPageDataSourceFactory : DataSource.Factory<Long, News.StoriesBean>() {
    override fun create(): DataSource<Long, News.StoriesBean> = ByPageDataSource()
}