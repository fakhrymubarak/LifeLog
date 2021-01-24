package com.fakhry.lifelog.ui.activities.edit

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
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
import com.fakhry.lifelog.ui.adapters.TagsAdapter
import com.fakhry.lifelog.viewmodel.ViewModelFactory
import kotlin.properties.Delegates

class AddUpdateActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityAddUpdateBinding
    private lateinit var addUpdateViewModel: AddUpdateViewModel
    private var timeMillisCreated by Delegates.notNull<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUpdateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val factory = ViewModelFactory.getInstance(this)
        addUpdateViewModel = ViewModelProvider(this, factory)[AddUpdateViewModel::class.java]

        timeMillisCreated = System.currentTimeMillis()
        supportActionBar?.hide()

        populateView()
        binding.btnSaveEdit.setOnClickListener(this)
        binding.btnCancelEdit.setOnClickListener(this)
    }

    private fun populateView() {
        // If new note then
        binding.tvTimestampAdd.text = BaseFunction(this).getFormalDate(timeMillisCreated, true)
        // if update then
        addUpdateViewModel.getNoteWithEditLogs(1611481441960).observe(this, { notes ->
            if (notes != null) {
                for (editLog in notes.listEditLogEntity) {
                    Log.d(
                        "asdasf",
                        "\nnote date : ${notes.note.noteCreatedDate}\t|\t" +
                                "title : ${notes.note.title}\t|\t" +
                                "edit date : ${editLog.noteEditDate}\t|\t" +
                                "desc : ${editLog.editDescription}"
                    )
                }
            }
        })
    }


    override fun onClick(v: View?) {
        when (v) {
            binding.btnCancelEdit -> {
                onBackPressed()
            }
            binding.btnSaveEdit -> {
//                showSaveDialog()
                saveToDatabase()
            }
        }
    }

    override fun onBackPressed() {
        showCancelDialog()
    }

    private fun showCancelDialog() {
        val mDialogView = Dialog(this)
        val popUpBinding = PopUpCancelEditBinding.inflate(LayoutInflater.from(this))
        mDialogView.setContentView(popUpBinding.root)
        mDialogView.window?.setBackgroundDrawableResource(android.R.color.transparent)
        mDialogView.show()

        popUpBinding.btnConfirmDialogCancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
            mDialogView.dismiss()
        }

        popUpBinding.btnCloseDialogCancel.setOnClickListener {
            mDialogView.dismiss()
        }
    }

    private fun showSaveDialog() {
        val tagsAdapter = TagsAdapter()
        val mDialogView = Dialog(this)
        val popUpBinding = PopUpSaveBinding.inflate(LayoutInflater.from(this))
        mDialogView.setContentView(popUpBinding.root)
        mDialogView.window?.setBackgroundDrawableResource(android.R.color.transparent)
        mDialogView.show()

        popUpBinding.btnBad.setOnClickListener {
            popUpBinding.seekBar.progress = 0
        }
        popUpBinding.btnNeutral.setOnClickListener {
            popUpBinding.seekBar.progress = 5
        }
        popUpBinding.btnGreat.setOnClickListener {
            popUpBinding.seekBar.progress = 10
        }

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
        popUpBinding.etAddTags.addTextChangedListener {
            if (it != null) {
                // ADD TO LIST AND EMPTY THE TEXT
                if (it.lastIndex > 0) {
                    if (it[it.lastIndex] == ' ' || it[it.lastIndex] == ',') {
                        listTags.add(it.toString().trim())
                        popUpBinding.rvTags.visibility = View.VISIBLE
                        setTagsRecyclerView(tagsAdapter, popUpBinding.rvTags, listTags)
                        it.delete(0, it.lastIndex)
                    }
                }

                // REMOVE LAST LIST IF DELETED
                if (it.isEmpty()) {
                    if (listTags.isNotEmpty()) {
                        listTags.removeLast()
                        it.insert(0, " ")
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
            val intent = Intent(this, MainActivity::class.java)
            saveToDatabase()
            startActivity(intent)
            finishAffinity()
            mDialogView.dismiss()
        }

        popUpBinding.btnCancelDialogSave.setOnClickListener {
            mDialogView.dismiss()
        }

        popUpBinding.btnCloseDialogSave.setOnClickListener {
            mDialogView.dismiss()
        }
    }

    private fun setTagsRecyclerView(
        tagsAdapter: TagsAdapter,
        rvTags: RecyclerView,
        listTags: List<String>
    ) {
        tagsAdapter.notifyDataSetChanged()
        tagsAdapter.setData(listTags)
        Log.d("adasd", "setTagsRecyclerView 2 = $listTags")
        rvTags.setHasFixedSize(true)
        rvTags.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvTags.adapter = tagsAdapter
    }

    private fun saveToDatabase() {
        val note = NoteEntity(
            noteCreatedDate = timeMillisCreated,
            createdDate = BaseFunction(this).getFormalDate(timeMillisCreated, false),
            title = "Hello World",
            description = "Halo dunia, semoga kamu baik baik saja dan sehat selalu.",
            isFavNote = false,
            lastUpdate = timeMillisCreated
        )
        val listTag: List<TagEntity> = listOf(
            TagEntity("why", timeMillisCreated),
            TagEntity("when", timeMillisCreated),
            TagEntity("who", timeMillisCreated),
        )

        val editLog = EditLogEntity(
            noteEditDate = timeMillisCreated,
            editDescription = "Correct Typo",
            noteCreatedDate = 1611481441960
        )


        addUpdateViewModel.insertNote(note)
        listTag.forEach { tagEntity ->
            val noteTagCrossRef = NoteTagCrossRef(
                noteCreatedDate = timeMillisCreated,
                tagName = tagEntity.tagName
            )
            addUpdateViewModel.insertNoteTagCrossRef(noteTagCrossRef)
            addUpdateViewModel.insertTag(tagEntity)
        }
        addUpdateViewModel.insertEditLog(editLog)
    }
}