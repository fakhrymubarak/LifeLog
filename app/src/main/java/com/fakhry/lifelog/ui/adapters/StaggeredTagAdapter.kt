package com.fakhry.lifelog.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.lifelog.data.local.entities.TagEntity
import com.fakhry.lifelog.databinding.ItemRowTagBinding

class StaggeredTagAdapter : RecyclerView.Adapter<StaggeredTagAdapter.GridViewHolder>() {

    inner class GridViewHolder(private val binding: ItemRowTagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: TagEntity) {
            with(binding) {
                tvTag.text = tag.tagName
            }
        }
    }

    private val tags = ArrayList<TagEntity>()

    fun setData(items: List<TagEntity>) {
        tags.clear()
        tags.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GridViewHolder {
        val itemRowTagBinding =
            ItemRowTagBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return GridViewHolder(itemRowTagBinding)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val tag = tags[position]
        holder.bind(tag)
    }

    override fun getItemCount(): Int {
        return tags.size
    }

}