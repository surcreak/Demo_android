package com.example.demo_view_app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.demo_view.itemdecoration.DividerItemDecoration
import com.example.demo_view.itemdecoration.GroupInfo
import com.example.demo_view.itemdecoration.StatickItemDecoration
import kotlinx.android.synthetic.main.activity_itemdecoration.*

class ItemDecorationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itemdecoration)

        recycle.adapter = TestAdapter(getTestData())
        recycle.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycle.addItemDecoration(StatickItemDecoration(object : StatickItemDecoration.GroupInfoCallBack{
            override fun getGroupInfo(position: Int): GroupInfo {
                var groupId = position/5
                var index = position%5
                return GroupInfo(groupId, groupId.toString(), index, 5)
            }
        }))
//        recycle.addItemDecoration(DividerItemDecoration())
    }

    private fun getTestData(): List<String> {
        val data = ArrayList<String>()
        for (i in 0 until 100) {
            data.add("test data $i")
        }
        return data
    }
}

class TestAdapter(var data: List<String>) : RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TestViewHolder {
        var v = LayoutInflater.from(viewGroup?.context).inflate(R.layout.item_decoration_item, viewGroup, false)
        return TestViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(viewHolder: TestViewHolder, position: Int) {
        viewHolder.testText.text = data[position]
    }

    class TestViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var testText = view.findViewById<TextView>(R.id.test_text)!!
    }
}

