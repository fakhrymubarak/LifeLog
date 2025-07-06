package com.fakhry.lifelog.components.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.lifelog.components.databinding.ItemRowDateWithNoteBinding
import com.fakhry.lifelog.core.database.model.DateNoteEntity
import com.fakhry.lifelog.core.database.model.NoteEntity

class ListDateWithNoteAdapter :
    ListAdapter<DateNoteEntity, ListDateWithNoteAdapter.ListViewHolder>(object :
        DiffUtil.ItemCallback<DateNoteEntity>() {
        override fun areItemsTheSame(oldItem: DateNoteEntity, newItem: DateNoteEntity): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: DateNoteEntity, newItem: DateNoteEntity): Boolean {
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

    private fun setChildRecyclerView(
        binding: ItemRowDateWithNoteBinding,
        notes: List<NoteEntity>
    ) {
        val childNoteAdapter = ListNoteAdapter()
        binding.rvNote.apply {
            setHasFixedSize(true)
            adapter = childNoteAdapter
        }
        childNoteAdapter.submitList(notes)
    }

    inner class ListViewHolder(private val binding: ItemRowDateWithNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dateNote: DateNoteEntity) {
            with(binding) {
                tvShowDate.text = dateNote.date
                setChildRecyclerView(this, dateNote.listNote)
            }
        }
    }
}
