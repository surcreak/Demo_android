package com.example.gl.demo_android.paging.itemkeyeddatasource

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
    @GET("users")
    fun getGithubAccount(@Query("since") id: Long,
                         @Query("per_page") perpage: Int):
            Observable<List<GithubAccount>>
}