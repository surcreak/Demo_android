package com.example.gl.demo_android.paging.itemkeyeddatasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource

class ByItemDataSourceFactory : DataSource.Factory<Long, GithubAccount>() {
    val byItemDataSource = MutableLiveData<ByItemDataSource>();
    override fun create(): DataSource<Long, GithubAccount> {
        val source = ByItemDataSource()
        byItemDataSource.postValue(source)
        return source
    }
}
