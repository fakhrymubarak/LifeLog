package com.fakhry.lifelog.components.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.lifelog.resources.R
import com.fakhry.lifelog.components.databinding.ItemRowNoteBinding
import com.fakhry.lifelog.navigation.Router
import com.fakhry.lifelog.storage.model.NoteEntity

class ListNoteAdapter : RecyclerView.Adapter<ListNoteAdapter.ListViewHolder>() {
    inner class ListViewHolder(private val binding: ItemRowNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteEntity) {
            with(binding) {
                tvShowTitle.text = note.title
                tvShowDesc.text = note.description
                when {
                    note.moodIndicator <= 3 -> {
                        ivNoteMood.setImageResource(R.drawable.ic_mood_indicator_bad_40px)
                    }
                    note.moodIndicator <= 6 -> {
                        ivNoteMood.setImageResource(R.drawable.ic_mood_indicator_neutral_40px)
                    }
                    note.moodIndicator <= 10 -> {
                        ivNoteMood.setImageResource(R.drawable.ic_mood_indicator_great_40px)
                    }
                }
                itemView.setOnClickListener {
                    Router.navigateToRead(itemView.context, note.noteCreatedDate)
                }
            }
        }
    }

    private val listNotes = ArrayList<NoteEntity>()

    fun setData(items: List<NoteEntity>) {
        listNotes.clear()
        listNotes.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemRowNoteBinding =
            ItemRowNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemRowNoteBinding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dateNoteEntity = listNotes[position]
        holder.bind(dateNoteEntity)
    }

    override fun getItemCount(): Int = listNotes.size
}