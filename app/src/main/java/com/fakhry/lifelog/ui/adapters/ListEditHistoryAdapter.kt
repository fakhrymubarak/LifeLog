package com.fakhry.lifelog.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.lifelog.R
import com.fakhry.lifelog.base.BaseFunction
import com.fakhry.lifelog.data.local.entities.EditLogEntity
import com.fakhry.lifelog.databinding.ItemRowEditedBinding

class ListEditHistoryAdapter : RecyclerView.Adapter<ListEditHistoryAdapter.ListViewHolder>() {
    inner class ListViewHolder(
        private val binding: ItemRowEditedBinding,
        private val parent: ViewGroup
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(editLogEntity: EditLogEntity, position: Int) {
            with(binding) {
                val editDesc = editLogEntity.editDescription
                val editDate =
                    BaseFunction(parent.context).getFormalDate(editLogEntity.noteEditDate, true)

                /*Edited-1 at Sunday, 17 January 2021, 11:21 - Correct Typo*/
                tvEditDesc.text = tvEditDesc.context.getString(
                    R.string.show_edit_date,
                    position,
                    editDate,
                    editDesc
                )
            }
        }
    }

    private val listEdits = ArrayList<EditLogEntity>()

    fun setData(items: List<EditLogEntity>) {
        listEdits.clear()
        listEdits.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemRowEditedBinding =
            ItemRowEditedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemRowEditedBinding, parent)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dateNoteEntity = listEdits[position]
        holder.bind(dateNoteEntity, position + 1)
    }

    override fun getItemCount(): Int = listEdits.size
}