package com.fakhry.lifelog.details.ui.read

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fakhry.lifelog.R
import com.fakhry.lifelog.components.adapters.ListEditHistoryAdapter
import com.fakhry.lifelog.components.adapters.StaggeredTagAdapter
import com.fakhry.lifelog.details.databinding.ActivityReadBinding
import com.fakhry.lifelog.details.databinding.PopUpDeleteNoteBinding
import com.fakhry.lifelog.navigation.Router
import com.fakhry.lifelog.storage.model.EditLogEntity
import com.fakhry.lifelog.storage.model.NoteEntity
import com.fakhry.lifelog.storage.model.TagEntity
import com.fakhry.lifelog.utils.getFormalDate

class ReadActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityReadBinding
    private lateinit var readViewModel: ReadViewModel
    private lateinit var noteEntity: NoteEntity

    companion object {
        const val EXTRA_NOTE = "extra_note"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityReadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ReadViewModel.provideFactory(this)
        readViewModel = ViewModelProvider(this, factory)[ReadViewModel::class.java]

        populateView()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnFavRead -> {
                changeFavState()
                setFavIcon()
            }
            binding.btnEditNote -> Router.navigateToEdit(this, noteEntity.noteCreatedDate)
            binding.btnDeleteNote -> showDeleteDialog()
            binding.btnBack -> onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun changeFavState() {
        noteEntity.isFavNote = !noteEntity.isFavNote
        readViewModel.favNote(noteEntity)
    }

    private fun setFavIcon() {
        if (noteEntity.isFavNote) {
            binding.btnFavRead.setImageResource(R.drawable.ic_star_fill_24px_white)
        } else {
            binding.btnFavRead.setImageResource(R.drawable.ic_star_outline_24px_white)
        }
    }

    private fun showDeleteDialog() {
        val mDialogView = Dialog(this)
        val popUpBinding = PopUpDeleteNoteBinding.inflate(LayoutInflater.from(this))
        mDialogView.setContentView(popUpBinding.root)
        mDialogView.window?.setBackgroundDrawableResource(android.R.color.transparent)
        mDialogView.show()

        popUpBinding.btnConfirmDelete.setOnClickListener {
            deleteNote()
            finishAffinity()
            mDialogView.dismiss()
        }

        popUpBinding.btnCancelDelete.setOnClickListener {
            mDialogView.dismiss()
        }

        popUpBinding.btnCloseDialogDelete.setOnClickListener {
            mDialogView.dismiss()
        }
    }

    private fun deleteNote() {
        readViewModel.deleteNote(noteEntity)
//        readViewModel.deleteTagsCrossRef(noteEntity.noteCreatedDate)
    }

    private fun populateView() {
        val extras = intent.extras
        val data = intent.data
        if (extras != null && extras.containsKey(EXTRA_NOTE)) {
            val idNote = extras.getLong(EXTRA_NOTE)
            populateView(idNote)
        } else if (data != null && data.queryParameterNames.contains(EXTRA_NOTE)) {
            val idNote = data.getQueryParameter(EXTRA_NOTE)?.toLong() ?: return
            populateView(idNote)
        }
    }

    private fun populateView(idNote: Long) {
        readViewModel.getNoteDetailsWithEdit(idNote).observe(this) { noteEdit ->
            noteEntity = noteEdit.note
            setFavIcon()
            with(binding) {
                tvTimestampAdd.text = getFormalDate(idNote, true)
                tvReadTitle.text = noteEntity.title
                tvAddDescription.text = noteEntity.description

                btnFavRead.setOnClickListener(this@ReadActivity)
                btnEditNote.setOnClickListener(this@ReadActivity)
                btnDeleteNote.setOnClickListener(this@ReadActivity)
                btnBack.setOnClickListener(this@ReadActivity)
            }
            if (noteEdit.listEditLogEntity.isNotEmpty()) {
                binding.rvEditHistory.visibility = View.VISIBLE
                setEditHistoryRecyclerView(noteEdit.listEditLogEntity)
            }
        }

        readViewModel.getNoteDetailsWithTag(idNote).observe(this) { noteTags ->
            if (noteTags.tags.isNotEmpty()) {
                binding.tvTags.visibility = View.VISIBLE
                binding.rvTags.visibility = View.VISIBLE
                setTagsRecyclerView(noteTags.tags)
            }
        }
    }

    private fun setEditHistoryRecyclerView(listEditLogEntity: List<EditLogEntity>) {
        binding.rvEditHistory.setHasFixedSize(true)
        val listEditHistoryAdapter = ListEditHistoryAdapter()
        listEditHistoryAdapter.notifyDataSetChanged()
        listEditHistoryAdapter.setData(listEditLogEntity)

        binding.rvEditHistory.layoutManager =
            LinearLayoutManager(binding.rvEditHistory.context, LinearLayoutManager.VERTICAL, false)
        binding.rvEditHistory.adapter = listEditHistoryAdapter
    }

    private fun setTagsRecyclerView(tags: List<TagEntity>) {
        binding.rvTags.setHasFixedSize(true)
        val staggeredAdapter = StaggeredTagAdapter()
        staggeredAdapter.notifyDataSetChanged()
        staggeredAdapter.setData(tags)

        binding.rvTags.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL)
        binding.rvTags.adapter = staggeredAdapter
    }
}