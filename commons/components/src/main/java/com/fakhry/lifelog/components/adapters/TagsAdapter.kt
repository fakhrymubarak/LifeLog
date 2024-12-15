package com.fakhry.lifelog.components.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.lifelog.components.databinding.ItemRowTagBinding

class TagsAdapter : RecyclerView.Adapter<TagsAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemRowTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: String) {
            binding.tvTag.text = tag
        }
    }

    private val listTags = ArrayList<String>()

    fun setData(items: List<String>) {
        listTags.clear()
        listTags.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val itemRowTagBinding =
            ItemRowTagBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(itemRowTagBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val tagName = listTags[position]
        holder.bind(tagName)
    }

    override fun getItemCount(): Int = listTags.size
}