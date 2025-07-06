package com.fakhry.lifelog.components.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.lifelog.components.databinding.ItemRowNoteBinding
import com.fakhry.lifelog.core.database.model.NoteEntity
import com.fakhry.lifelog.core.ui.R
import com.fakhry.lifelog.navigation.Router

class ListNoteAdapter : ListAdapter<NoteEntity, ListNoteAdapter.ListViewHolder>(
    object : DiffUtil.ItemCallback<NoteEntity>() {
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.noteCreatedDate == newItem.noteCreatedDate // assuming it's unique
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }
    }
) {

    inner class ListViewHolder(private val binding: ItemRowNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteEntity) {
            with(binding) {
                tvShowTitle.text = note.title
                tvShowDesc.text = note.description

                ivNoteMood.setImageResource(
                    when {
                        note.moodIndicator <= 3 -> R.drawable.ic_mood_indicator_bad_40px
                        note.moodIndicator <= 6 -> R.drawable.ic_mood_indicator_neutral_40px
                        else -> R.drawable.ic_mood_indicator_great_40px
                    }
                )

                itemView.setOnClickListener {
                    Router.navigateToRead(itemView.context, note.noteCreatedDate)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowNoteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
