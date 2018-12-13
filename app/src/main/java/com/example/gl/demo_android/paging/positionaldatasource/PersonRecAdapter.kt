package com.example.gl.demo_android.paging.positionaldatasource

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gl.demo_android.R

class PersonRecAdapter(val context: Context): PagedListAdapter<Person, PersonRecAdapter.PersonViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(p0: Person, p1: Person): Boolean {
                return p0.id == p1.id
            }

            override fun areContentsTheSame(p0: Person, p1: Person): Boolean {
                return p0 == p1
            }

            override fun getChangePayload(oldItem: Person, newItem: Person): Any? {
                return null
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PersonViewHolder {
        return PersonViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.person_rec_item, p0, false))
    }

    override fun onBindViewHolder(p0: PersonViewHolder, p1: Int) {
        p0.bindTo(getItem(p1))
    }

    class PersonViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
        val id = itemView.findViewById<TextView>(R.id.id)
        val name = itemview.findViewById<TextView>(R.id.name)

        fun bindTo(person: Person?){
            id.text = person!!.id.toString()
            name.text = person.name
        }
    }
}