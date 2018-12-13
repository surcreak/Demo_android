package com.example.gl.demo_android.paging.pagekeydatasource

import android.arch.paging.PageKeyedDataSource
import com.example.gl.demo_android.paging.ExecuteOnceObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class ByPageDataSource : PageKeyedDataSource<Long, News.StoriesBean>() {

    private lateinit var mNewsService: NewsService
    private val mData = Calendar.getInstance().apply {
        add(Calendar.DATE, 1)
    }

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<Long, News.StoriesBean>) {
        mNewsService = NewsService.ApiGenerate.getNewsService()
        mNewsService.getNews(SimpleDateFormat("yyyyMMdd",
                Locale.CHINA).format(mData.time).toLong())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe (ExecuteOnceObserver(onExecuteOnceNext = {
                    callback.onResult(it.stories, 0, it.date.toLong())
                }))
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, News.StoriesBean>) {
        mNewsService.getNews(params.key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                    callback.onResult(it.stories, it.date.toLong())
                }))
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, News.StoriesBean>) {
        //这里不需要向上加载，因此无须实现
    }

}