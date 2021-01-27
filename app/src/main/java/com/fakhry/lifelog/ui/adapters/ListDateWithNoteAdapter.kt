package com.fakhry.lifelog.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.lifelog.data.local.entities.DateNoteEntity
import com.fakhry.lifelog.data.local.entities.NoteEntity
import com.fakhry.lifelog.databinding.ItemRowDateWithNoteBinding
import kotlin.collections.ArrayList

class ListDateWithNoteAdapter : RecyclerView.Adapter<ListDateWithNoteAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemRowDateWithNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dateNote: DateNoteEntity) {
            with(binding) {
                tvShowDate.text = dateNote.date
                setChildRecyclerView(this, dateNote.listNote)
            }
        }
    }

    private val listNotes = ArrayList<DateNoteEntity>()

    fun setData(items: List<DateNoteEntity>) {
        listNotes.clear()
        listNotes.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemRowDateWithNoteBinding =
            ItemRowDateWithNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemRowDateWithNoteBinding)
    }


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dateNoteEntity = listNotes[position]
        holder.bind(dateNoteEntity)
    }

    private fun setChildRecyclerView(binding: ItemRowDateWithNoteBinding, note: List<NoteEntity>) {
        binding.rvNote.setHasFixedSize(true)
        val childNoteAdapter = ListNoteAdapter()
        childNoteAdapter.notifyDataSetChanged()
        childNoteAdapter.setData(note)

        binding.rvNote.layoutManager =
            LinearLayoutManager(binding.rvNote.context, LinearLayoutManager.VERTICAL, false)
        binding.rvNote.adapter = childNoteAdapter
    }

    override fun getItemCount(): Int = listNotes.size
}