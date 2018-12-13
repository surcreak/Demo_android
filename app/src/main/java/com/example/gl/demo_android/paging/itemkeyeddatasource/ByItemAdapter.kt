package com.example.gl.demo_android.paging.itemkeyeddatasource

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gl.demo_android.R
import com.example.gl.demo_android.utils.DemoLog

class ByItemAdapter(val context: Context) : PagedListAdapter<GithubAccount, ByItemAdapter.ByItemViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<GithubAccount>() {
            override fun areItemsTheSame(p0: GithubAccount, p1: GithubAccount): Boolean {
                return p0.id == p1.id
            }

            override fun areContentsTheSame(p0: GithubAccount, p1: GithubAccount): Boolean {
                return p0 == p1
            }

            override fun getChangePayload(oldItem: GithubAccount, newItem: GithubAccount): Any? {
                return null
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ByItemViewHolder {
        DemoLog.pagingLog("onCreateViewHolder")
        DemoLog.pagingLog("itemCount="+ itemCount)
        return ByItemViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.by_item_rec_adapter, p0, false))
    }

    override fun onBindViewHolder(p0: ByItemViewHolder, p1: Int) {
        val item: GithubAccount = getItem(p1) as GithubAccount
        DemoLog.pagingLog("onBindViewHolder")
        DemoLog.pagingLog(getItem(p1).toString())
        p0.bindto(getItem(p1))
    }

    override fun getItemCount(): Int {
        val count: Int =  super.getItemCount()
        DemoLog.pagingLog("count="+count)
//        DemoLog.pagingLog("currentList.isEmpty()="+ currentList!!.isEmpty())
        return count
    }

    class ByItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mId: TextView = itemView.findViewById(R.id.id)
        private val mName: TextView = itemView.findViewById(R.id.name)

        fun bindto(account: GithubAccount?) {
            account?.let {
                mId.text = it.id.toString()
                mName.text = it.login
            }
        }
    }
}