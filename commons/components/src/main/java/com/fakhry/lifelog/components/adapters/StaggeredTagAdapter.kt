package com.fakhry.lifelog.components.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.lifelog.components.databinding.ItemRowTagBinding
import com.fakhry.lifelog.domain.model.TagDomain

class StaggeredTagAdapter : ListAdapter<TagDomain, StaggeredTagAdapter.GridViewHolder>(object :
    DiffUtil.ItemCallback<TagDomain>() {
    override fun areItemsTheSame(oldItem: TagDomain, newItem: TagDomain): Boolean {
        return oldItem.noteCreatedDate == newItem.noteCreatedDate
    }

    override fun areContentsTheSame(oldItem: TagDomain, newItem: TagDomain): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val binding = ItemRowTagBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return GridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class GridViewHolder(private val binding: ItemRowTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: TagDomain) {
            binding.tvTag.text = tag.tagName
        }
    }
}
