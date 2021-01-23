package com.fakhry.lifelog.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.lifelog.R
import kotlin.collections.ArrayList

class TagsAdapter : RecyclerView.Adapter<TagsAdapter.ListViewHolder>() {
    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvCategory : TextView = itemView.findViewById(R.id.tv_tag)
    }

    private val listTags = ArrayList<String>()

    fun setData(items: List<String>) {
        listTags.clear()
        listTags.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_tag, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val tags = listTags[position]
        holder.tvCategory.text = tags
    }

    override fun getItemCount(): Int = listTags.size
}