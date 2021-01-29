package com.fakhry.lifelog.ui.activities.read

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.fakhry.lifelog.R
import com.fakhry.lifelog.base.BaseFunction
import com.fakhry.lifelog.data.local.entities.EditLogEntity
import com.fakhry.lifelog.data.local.entities.NoteEntity
import com.fakhry.lifelog.data.local.entities.TagEntity
import com.fakhry.lifelog.databinding.ActivityReadBinding
import com.fakhry.lifelog.databinding.PopUpDeleteNoteBinding
import com.fakhry.lifelog.ui.activities.edit.AddUpdateActivity
import com.fakhry.lifelog.ui.activities.main.MainActivity
import com.fakhry.lifelog.ui.adapters.StaggeredTagAdapter
import com.fakhry.lifelog.ui.adapters.ListEditHistoryAdapter
import com.fakhry.lifelog.viewmodel.ViewModelFactory

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

        val factory = ViewModelFactory.getInstance(this)
        readViewModel = ViewModelProvider(this, factory)[ReadViewModel::class.java]

        populateView()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnFavRead -> {
                changeFavState()
                setFavIcon()
            }
            binding.btnEditNote -> {
                val intent = Intent(this, AddUpdateActivity::class.java)
                intent.putExtra(EXTRA_NOTE, noteEntity.noteCreatedDate)
                startActivity(intent)
            }
            binding.btnDeleteNote -> {
                showDeleteDialog()
            }
            binding.btnBack -> {
                onBackPressed()
            }
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
            val intent = Intent(this, MainActivity::class.java)
            deleteNote()
            startActivity(intent)
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
        if (extras != null) {
            val idNote = extras.getLong(EXTRA_NOTE)
            populateView(idNote)
        }
    }

    private fun populateView(idNote: Long) {
        readViewModel.getNoteDetailsWithEdit(idNote).observe(this, { noteEdit ->
            noteEntity = noteEdit.note
            setFavIcon()
            with(binding) {
                tvTimestampAdd.text = BaseFunction(this@ReadActivity).getFormalDate(idNote, true)
                tvReadTitle.text = noteEntity.title
                tvAddDescription.text = noteEntity.description

                btnFavRead.setOnClickListener(this@ReadActivity)
                btnEditNote.setOnClickListener(this@ReadActivity)
                btnDeleteNote.setOnClickListener(this@ReadActivity)
                btnBack.setOnClickListener(this@ReadActivity)
            }
            if (!noteEdit.listEditLogEntity.isNullOrEmpty()) {
                binding.rvEditHistory.visibility = View.VISIBLE
                setEditHistoryRecyclerView(noteEdit.listEditLogEntity)
            }
        })

        readViewModel.getNoteDetailsWithTag(idNote).observe(this, { noteTags ->
            if (!noteTags.tags.isNullOrEmpty()) {
                binding.tvTags.visibility = View.VISIBLE
                binding.rvTags.visibility = View.VISIBLE
                setTagsRecyclerView(noteTags.tags)
            }
        })
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