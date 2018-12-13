package com.example.gl.demo_android.paging.pagekeydatasource

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.gl.demo_android.R

class ByPageAdapter : PagedListAdapter<News.StoriesBean,ByPageAdapter.ByItemViewHolder>(diffCallback) {
    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<News.StoriesBean>() {
            override fun areItemsTheSame(p0: News.StoriesBean, p1: News.StoriesBean): Boolean {
                return p0.id == p1.id
            }

            override fun areContentsTheSame(p0: News.StoriesBean, p1: News.StoriesBean): Boolean {
                return p0 == p1
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ByPageAdapter.ByItemViewHolder {
        return ByItemViewHolder(LayoutInflater.from(p0.context)
                .inflate(
                        R.layout.by_page_rec_item,
                        p0,
                        false
                ))
    }

    override fun onBindViewHolder(p0: ByPageAdapter.ByItemViewHolder, p1: Int) {
        p0.bindTo(getItem(p1))
    }

    class ByItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var imageView: ImageView
        private lateinit var textView: TextView

        fun bindTo(story: News.StoriesBean?) {
            imageView = itemView.findViewById(R.id.iv)
            textView = itemView.findViewById(R.id.tv)
        }
    }

}
