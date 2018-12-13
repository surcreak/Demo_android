package com.example.gl.demo_android.paging.itemkeyeddatasource

import android.arch.paging.ItemKeyedDataSource
import com.example.gl.demo_android.paging.ExecuteOnceObserver
import com.example.gl.demo_android.utils.DemoLog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ByItemDataSource : ItemKeyedDataSource<Long, GithubAccount>() {

    private val mGitHubService by lazy {
        ApiGenerate.getGithubService()
    }

    //此方法之后在用DataSource构建PageList的时候才会调用一次。用于进行加载初始化。
    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<GithubAccount>) {
        DemoLog.pagingLog("loadInitial")
        mGitHubService.getGithubAccount(0, params.requestedLoadSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe {
                    callback.onResult(it)
                }
//                .subscribe(ExecuteOnceObserver({
//                    DemoLog.pagingLog("loadInitial subscribe")
//                    callback.onResult(it)
//
////                    for (githubAccount in it) {
////                        DemoLog.pagingLog(githubAccount.toString())
////                    }
//                }))
    }

    //在每次RecyclerView滑动到底部没有数据的时候就会调用此方法进行数据的加载。
    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<GithubAccount>) {
        DemoLog.pagingLog("loadAfter")
        mGitHubService.getGithubAccount(params.key, params.requestedLoadSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(ExecuteOnceObserver(onExecuteOnceNext = {
                    callback.onResult(it)
                }))
    }

    //在每次RecyclerView滑动到顶部没有数据的时候就会调用此方法进行数据的加载。
    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<GithubAccount>) {
        //由于这里不需要向上加载因此省略此处
    }

    //这返回下一个loadAfter调用所需要用到的key。就相当于链表的指针。
    override fun getKey(item: GithubAccount): Long = item.id.toLong()

}