package com.fakhry.lifelog.ui.activities.edit

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.lifelog.R
import com.fakhry.lifelog.base.BaseFunction
import com.fakhry.lifelog.data.local.entities.EditLogEntity
import com.fakhry.lifelog.data.local.entities.NoteEntity
import com.fakhry.lifelog.data.local.entities.TagEntity
import com.fakhry.lifelog.data.local.relation.NoteTagCrossRef
import com.fakhry.lifelog.databinding.ActivityAddUpdateBinding
import com.fakhry.lifelog.databinding.PopUpCancelEditBinding
import com.fakhry.lifelog.databinding.PopUpSaveBinding
import com.fakhry.lifelog.ui.activities.main.MainActivity
import com.fakhry.lifelog.ui.activities.read.ReadActivity
import com.fakhry.lifelog.ui.adapters.TagsAdapter
import com.fakhry.lifelog.viewmodel.ViewModelFactory
import kotlin.properties.Delegates

class AddUpdateActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityAddUpdateBinding
    private lateinit var addUpdateViewModel: AddUpdateViewModel
    private lateinit var noteEntity: NoteEntity
    private var timeMillisCreated by Delegates.notNull<Long>()
    private var isCreate by Delegates.notNull<Boolean>()
    private var isChange = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityAddUpdateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val factory = ViewModelFactory.getInstance(this)
        addUpdateViewModel = ViewModelProvider(this, factory)[AddUpdateViewModel::class.java]

        populateView()
        binding.btnSaveEdit.setOnClickListener(this)
        binding.btnCancelEdit.setOnClickListener(this)
        binding.etNoteDesc.addTextChangedListener { isChange = true }
        binding.etNoteTitle.addTextChangedListener { isChange = true }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnCancelEdit -> {
                onBackPressed()
            }
            binding.btnSaveEdit -> {
                this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                if (binding.etNoteTitle.text.toString().trim().isEmpty()) {
                    if (binding.etNoteDesc.text.toString().trim().isEmpty()) {
                        Toast.makeText(this, R.string.toast_msg_empty_note, Toast.LENGTH_LONG)
                            .show()
                    } else {
                        Toast.makeText(this, R.string.toast_msg_empty_title, Toast.LENGTH_LONG)
                            .show()
                    }
                } else showSaveDialog()
            }
        }
    }

    override fun onBackPressed() {
        if (!isChange) {
            moveToDashboard()
        } else {
            showCancelDialog()
        }
    }

    private fun populateView() {
        val extras = intent.extras
        if (extras != null) {
            val idNote = extras.getLong(ReadActivity.EXTRA_NOTE)
            isCreate = false
            timeMillisCreated = idNote
            populateView(idNote)
        } else {
            timeMillisCreated = System.currentTimeMillis()
            binding.tvTimestampAdd.text = BaseFunction(this).getFormalDate(timeMillisCreated, true)
            isCreate = true
        }
    }

    private fun populateView(idNote: Long) {
        binding.tvTimestampAdd.text = BaseFunction(this).getFormalDate(idNote, true)
        addUpdateViewModel.getNoteWithEditLogs(idNote).observe(this) { notes ->
            noteEntity = notes.note
            binding.etNoteTitle.setText(notes.note.title)
            binding.etNoteDesc.setText(notes.note.description)
        }
    }

    private fun showCancelDialog() {
        val mDialogView = Dialog(this)
        val popUpBinding = PopUpCancelEditBinding.inflate(LayoutInflater.from(this))
        mDialogView.setContentView(popUpBinding.root)
        mDialogView.window?.setBackgroundDrawableResource(android.R.color.transparent)
        mDialogView.show()

        popUpBinding.btnConfirmDialogCancel.setOnClickListener {
            mDialogView.dismiss()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
        popUpBinding.btnCloseDialogCancel.setOnClickListener { mDialogView.dismiss() }
    }

    private fun showSaveDialog() {
        val mDialogView = Dialog(this)
        val popUpBinding = PopUpSaveBinding.inflate(LayoutInflater.from(this))
        mDialogView.setContentView(popUpBinding.root)
        mDialogView.window?.setBackgroundDrawableResource(android.R.color.transparent)
        mDialogView.show()

        if (isCreate) {
            popUpBinding.tvGiveComment.visibility = View.GONE
            popUpBinding.etAddComments.visibility = View.GONE
        } else {
            popUpBinding.tvTitlePopupSave.text = getString(R.string.popup_update_title)
            popUpBinding.seekBar.progress = noteEntity.moodIndicator
        }

        popUpBinding.btnBad.setOnClickListener { popUpBinding.seekBar.progress = 0 }
        popUpBinding.btnNeutral.setOnClickListener { popUpBinding.seekBar.progress = 5 }
        popUpBinding.btnGreat.setOnClickListener { popUpBinding.seekBar.progress = 10 }

        popUpBinding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                when {
                    progress <= 1 -> {
                        seekBar?.thumb?.setTint(
                            ContextCompat.getColor(
                                this@AddUpdateActivity,
                                R.color.danger500
                            )
                        )
                    }
                    progress <= 3 -> {
                        seekBar?.thumb?.setTint(
                            ContextCompat.getColor(
                                this@AddUpdateActivity,
                                R.color.danger200
                            )
                        )
                    }
                    progress <= 6 -> {
                        seekBar?.thumb?.setTint(
                            ContextCompat.getColor(
                                this@AddUpdateActivity,
                                R.color.gray500
                            )
                        )
                    }
                    progress <= 8 -> {
                        seekBar?.thumb?.setTint(
                            ContextCompat.getColor(
                                this@AddUpdateActivity,
                                R.color.success200
                            )
                        )
                    }
                    progress <= 10 -> {
                        seekBar?.thumb?.setTint(
                            ContextCompat.getColor(
                                this@AddUpdateActivity,
                                R.color.success500
                            )
                        )
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        val listTags = ArrayList<String>()
        val tagsAdapter = TagsAdapter()
//        addUpdateViewModel.getNoteDetailsWithTag(timeMillisCreated).observe(this, {
//            it?.tags?.forEach { tag ->
//                Log.d("asdkjaldjas", tag.tagName)
//                listTags.add(tag.tagName)
//                setTagsRecyclerView(tagsAdapter, popUpBinding.rvTags, listTags)
//            }
//        })

        popUpBinding.etAddTags.addTextChangedListener { text ->
            if (text != null) {
                // ADD TO LIST AND EMPTY THE TEXT
                if (text.lastIndex > 0) {
                    if (text[text.lastIndex] == ' ' || text[text.lastIndex] == ',') {
                        listTags.add(text.toString().trim())
                        popUpBinding.rvTags.visibility = View.VISIBLE
                        setTagsRecyclerView(tagsAdapter, popUpBinding.rvTags, listTags)
                        text.delete(0, text.lastIndex)
                    }
                }

                // REMOVE LAST LIST IF DELETED
                if (text.isEmpty()) {
                    if (listTags.isNotEmpty()) {
                        listTags.removeLast()
                        text.insert(0, " ")
                        setTagsRecyclerView(tagsAdapter, popUpBinding.rvTags, listTags)
                        if (listTags.isEmpty()) {
                            popUpBinding.rvTags.visibility = View.GONE
                        }
                    } else {
                        popUpBinding.rvTags.visibility = View.GONE
                    }
                }
            }
        }

        popUpBinding.btnConfirmDialogSave.setOnClickListener {
            insertNote(popUpBinding.seekBar.progress)
            insertTag(listTags)
            if (!isCreate) {
                insertEditHistory(popUpBinding.etAddComments.text.toString().trim())
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
            mDialogView.dismiss()
        }

        popUpBinding.btnCancelDialogSave.setOnClickListener { mDialogView.dismiss() }
        popUpBinding.btnCloseDialogSave.setOnClickListener { mDialogView.dismiss() }
    }

    private fun insertNote(progress: Int) {
        val noteTitle = binding.etNoteTitle.text.toString().trim()
        val noteDescription = binding.etNoteDesc.text.toString().trim()

        val note = NoteEntity(
            noteCreatedDate = timeMillisCreated,
            createdDate = BaseFunction(this).getFormalDate(timeMillisCreated, false),
            title = noteTitle,
            moodIndicator = progress,
            description = noteDescription,
            isFavNote = false,
            lastUpdate = System.currentTimeMillis()
        )
        addUpdateViewModel.insertNote(note)
    }

    private fun insertTag(tags: List<String>) {
        val listTagEntity = ArrayList<TagEntity>()
        tags.forEach { tag ->
            val tagEntity = TagEntity(tag, timeMillisCreated)
            listTagEntity.add(tagEntity)
        }


        listTagEntity.forEach { tagEntity ->
            val noteTagCrossRef = NoteTagCrossRef(
                noteCreatedDate = timeMillisCreated,
                tagName = tagEntity.tagName
            )
            addUpdateViewModel.insertNoteTagCrossRef(noteTagCrossRef)
            addUpdateViewModel.insertTag(tagEntity)
        }
    }

    private fun insertEditHistory(text: String) {
        val editLog = EditLogEntity(
            noteEditDate = System.currentTimeMillis(),
            editDescription = text,
            noteCreatedDate = timeMillisCreated
        )
        addUpdateViewModel.insertEditLog(editLog)
    }

    private fun setTagsRecyclerView(
        tagsAdapter: TagsAdapter,
        rvTags: RecyclerView,
        listTags: List<String>
    ) {
        tagsAdapter.notifyDataSetChanged()
        tagsAdapter.setData(listTags)
        rvTags.setHasFixedSize(true)
        rvTags.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvTags.adapter = tagsAdapter
    }

    private fun moveToDashboard() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }
}