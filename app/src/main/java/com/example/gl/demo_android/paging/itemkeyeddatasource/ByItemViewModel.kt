package com.example.gl.demo_android.paging.itemkeyeddatasource

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList

class ByItemViewModel(application: Application): AndroidViewModel(application) {

    companion object {
        private const val PAGE_SIZE = 15

        private const val ENABLE_PLACEHOLDERS = false
    }

    val githubAccounts = LivePagedListBuilder(ByItemDataSourceFactory(),
            PagedList.Config.Builder()
                    .setInitialLoadSizeHint(10)
                    .setPageSize(PAGE_SIZE)
                    .setPrefetchDistance(15)
                    .setEnablePlaceholders(ENABLE_PLACEHOLDERS).build())
            .build()
}