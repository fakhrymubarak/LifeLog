package com.fakhry.lifelog.components.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.lifelog.components.databinding.ItemRowEditedBinding
import com.fakhry.lifelog.core.ui.R
import com.fakhry.lifelog.domain.model.EditLogDomain
import com.fakhry.lifelog.utils.getFormalDate

class ListEditHistoryAdapter :
    ListAdapter<EditLogDomain, ListEditHistoryAdapter.ListViewHolder>(object :
        DiffUtil.ItemCallback<EditLogDomain>() {
        override fun areItemsTheSame(oldItem: EditLogDomain, newItem: EditLogDomain): Boolean {
            return oldItem.noteEditDate == newItem.noteEditDate
        }

        override fun areContentsTheSame(oldItem: EditLogDomain, newItem: EditLogDomain): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowEditedBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position), position + 1)
    }

    inner class ListViewHolder(
        private val binding: ItemRowEditedBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(editLogDomain: EditLogDomain, position: Int) {
            val editDesc = editLogDomain.editDescription
            val editDate = getFormalDate(editLogDomain.noteEditDate, true)

            /*Edited-1 at Sunday, 17 January 2021, 11:21 - Correct Typo*/
            binding.tvEditDesc.text = binding.tvEditDesc.context.getString(
                R.string.show_edit_date, position, editDate, editDesc
            )
        }
    }
}
