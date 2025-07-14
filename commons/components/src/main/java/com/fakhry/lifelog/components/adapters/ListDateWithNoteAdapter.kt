package com.fakhry.lifelog.components.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.lifelog.components.databinding.ItemRowDateWithNoteBinding
import com.fakhry.lifelog.domain.model.DateNoteDomain

class ListDateWithNoteAdapter :
    ListAdapter<DateNoteDomain, ListDateWithNoteAdapter.ListViewHolder>(object :
        DiffUtil.ItemCallback<DateNoteDomain>() {
        override fun areItemsTheSame(oldItem: DateNoteDomain, newItem: DateNoteDomain): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: DateNoteDomain, newItem: DateNoteDomain): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowDateWithNoteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dateNoteEntity = getItem(position)
        holder.bind(dateNoteEntity)
    }

    inner class ListViewHolder(private val binding: ItemRowDateWithNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val childNoteAdapter by lazy { ListNoteAdapter() }

        fun bind(dateNote: DateNoteDomain) {
            with(binding) {
                tvShowDate.text = dateNote.date

                binding.rvNote.apply {
                    setHasFixedSize(true)
                    adapter = childNoteAdapter
                }

                childNoteAdapter.submitList(dateNote.listNote)
            }
        }
    }
}
