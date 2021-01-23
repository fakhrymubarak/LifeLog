package com.fakhry.lifelog.ui.activities.edit

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fakhry.lifelog.R
import com.fakhry.lifelog.databinding.ActivityAddUpdateBinding
import com.fakhry.lifelog.databinding.PopUpCancelEditBinding
import com.fakhry.lifelog.databinding.PopUpSaveBinding
import com.fakhry.lifelog.ui.activities.main.MainActivity
import com.fakhry.lifelog.ui.adapters.TagsAdapter

class AddUpdateActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityAddUpdateBinding
    private lateinit var addUpdateViewModel: AddUpdateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUpdateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        populateView()
        binding.btnSaveEdit.setOnClickListener(this)
        binding.btnCancelEdit.setOnClickListener(this)
    }

    private fun populateView() {
        val timestamp1 = System.currentTimeMillis().toString() // put on database
        val dateFormatted = epochToDate(timestamp1) //use to show from database
        binding.tvTimestampAdd.text = dateFormatted
    }

    private fun epochToDate(dateInMilliseconds: String): String {
        val dateFormat = "EEE, dd MMM yyyy, HH:mm"
        return DateFormat.format(dateFormat, dateInMilliseconds.toLong()).toString()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnCancelEdit -> {
                onBackPressed()
            }
            binding.btnSaveEdit -> {
                showSaveDialog()
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
            Log.d("adasd", "text = $it")
            if (it != null) {
                // ADD TO LIST AND EMPTY THE TEXT
                if (it.lastIndex > 0) {
                    if (it[it.lastIndex] == ' ' || it[it.lastIndex] == ',') {
                        listTags.add(it.toString().trim())
                        Log.d("adasd", "listTags 1 = $listTags")
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
                        if(listTags.isEmpty()){
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
    }
}