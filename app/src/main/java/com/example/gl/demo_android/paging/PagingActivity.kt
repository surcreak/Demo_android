package com.example.gl.demo_android.paging

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.gl.demo_android.R
import com.example.gl.demo_android.paging.itemkeyeddatasource.ByItemAdapter
import com.example.gl.demo_android.paging.itemkeyeddatasource.ByItemViewModel
import com.example.gl.demo_android.paging.pagekeydatasource.ByPageAdapter
import com.example.gl.demo_android.paging.pagekeydatasource.ByPageViewModel
import com.example.gl.demo_android.paging.positionaldatasource.PersonRecAdapter
import com.example.gl.demo_android.paging.positionaldatasource.PersonViewModel
import com.example.gl.demo_android.utils.DemoLog
import kotlinx.android.synthetic.main.activity_paging.*

class PagingActivity : AppCompatActivity() {

    private lateinit var mPersonViewModel: PersonViewModel
    private lateinit var mByItemViewModel: ByItemViewModel
    private lateinit var mByPageViewModel: ByPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging)
        title = "paging"

        val userItem: Int = 2

        //PageKeyedDataSource：如果页面需要实现上一页、下一页，需要将请求的Token传递到下一步
        //ItemKeyedDataSource：程序需要根据上一条数据信息（ID）获取下一条数据时
        //PositionalDataSource：需要从数据存储中选择的任何位置获取数据页；例如，请求可能返回以位置1200开头的20个数据项

        // 1.positionalDataSource使用
        // java.lang.RuntimeException: cannot find implementation for
        // com.example.gl.demo_android.paging.positionaldatasource.PersonDatabase. PersonDatabase_Impl does not exist
        // 没加apply plugin: 'kotlin-kapt',在build.gradle里。

        if (userItem == 1) {
            mPersonViewModel = ViewModelProviders.of(this).get(PersonViewModel::class.java)
            val adpter = PersonRecAdapter(this)
            rv.adapter = adpter
            mPersonViewModel.persons.observe(this, Observer(adpter::submitList))
        }


        // 2.ItemKeyedDataSource使用
        // 一直没有出现数据，但通过log发现网络是获取了第一次的数据的
        if (userItem == 2) {
            mByItemViewModel = ViewModelProviders.of(this).get(ByItemViewModel::class.java)
            val adapter = ByItemAdapter(this)
            rv.adapter = adapter
            mByItemViewModel.githubAccounts.observe(this, Observer {
                DemoLog.pagingLog("pagingActivity: onChanged")
                DemoLog.pagingLog("pagingActivity: onChanged"+it)
                adapter::submitList
            })
            DemoLog.pagingLog("hasactiveObservers = "+mByItemViewModel.githubAccounts.hasActiveObservers())
        }

        // 3.PageKeyedDataSource使用
        if (userItem == 3) {
            mByPageViewModel = ViewModelProviders.of(this).get(ByPageViewModel::class.java)
            val adapter = ByPageAdapter()
            rv.adapter = adapter
            mByPageViewModel.stories.observe(this, Observer(adapter::submitList))
        }

    }
}