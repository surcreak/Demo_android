package com.example.gl.demo_android.paging.itemkeyeddatasource

import com.example.gl.demo_android.utils.OkHttpUtil
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiGenerate{
    private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpUtil.getOkHttpClient())
            .build()

    fun getGithubService(): GitHubService = retrofit.create(GitHubService::class.java)
}