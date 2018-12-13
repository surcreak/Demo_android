package com.example.gl.demo_android.paging.pagekeydatasource

import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList

class ByPageViewModel : ViewModel() {
    val stories = LivePagedListBuilder(ByPageDataSourceFactory(),
            PagedList.Config.Builder()
                    .setPageSize(30)
                    .setEnablePlaceholders(false).build())
            .build()
}