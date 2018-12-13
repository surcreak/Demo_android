package com.example.gl.demo_android.paging.pagekeydatasource

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {
    @GET("before/{time}")
    fun getNews(@Path("time")time: Long): Observable<News>

    object ApiGenerate {
        private val retrofit = Retrofit.Builder()
                .baseUrl("https://news-at.zhihu.com/api/4/news/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        fun getNewsService(): NewsService = retrofit.create(NewsService::class.java)
    }
}