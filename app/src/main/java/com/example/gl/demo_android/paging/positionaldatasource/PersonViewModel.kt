package com.example.gl.demo_android.paging.positionaldatasource

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList

class PersonViewModel(application: Application): AndroidViewModel(application) {
    companion object {
        private const val PAGE_SIZE = 30
        private const val ENABLE_PLACEHOLDER = true
    }

    private val mPersonDao = PersonDatabase.get(application).personDao()

    val persons = LivePagedListBuilder(mPersonDao.getAllPersons(),
            PagedList.Config.Builder()
                    .setPageSize(PAGE_SIZE)
                    .setEnablePlaceholders(ENABLE_PLACEHOLDER)
                    .build())
            .build()
}