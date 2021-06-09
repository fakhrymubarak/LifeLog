package com.fakhry.lifelog.ui.fragments.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhry.lifelog.base.BaseFunction
import com.fakhry.lifelog.data.local.entities.DateNoteEntity
import com.fakhry.lifelog.databinding.FragmentDashboardBinding
import com.fakhry.lifelog.ui.activities.edit.AddUpdateActivity
import com.fakhry.lifelog.ui.adapters.ListDateWithNoteAdapter
import com.fakhry.lifelog.viewmodel.ViewModelFactory

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var baseFunction: BaseFunction

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseFunction = BaseFunction(requireContext())

        val factory = ViewModelFactory.getInstance(requireContext())
        dashboardViewModel = ViewModelProvider(this, factory)[DashboardViewModel::class.java]

        getNoteBasedDate()
        binding.btnAddNote.setOnClickListener {
            val intent = Intent(requireContext(), AddUpdateActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getNoteBasedDate() {
        val listNoteDate = ArrayList<DateNoteEntity>()
        dashboardViewModel.getAllDates().observe(viewLifecycleOwner) { listDates ->
            listDates.forEach { date ->
                dashboardViewModel.getNoteBasedDate(date).observe(viewLifecycleOwner) { listNote ->
                    val dateNote = DateNoteEntity(date, listNote)
                    listNoteDate.add(dateNote)
                    setDashboardRecyclerView(listNoteDate)
                }
            }
        }
    }

    private fun setDashboardRecyclerView(dateNoteList: ArrayList<DateNoteEntity>) {
        if (dateNoteList.isNotEmpty()) {
            binding.rvDashboard.visibility = View.VISIBLE
            binding.ivEmptyDashboard.visibility = View.INVISIBLE
            binding.tvEmptyDashboard.visibility = View.INVISIBLE
            binding.btnAddNote.visibility = View.INVISIBLE
            binding.rvDashboard.setHasFixedSize(true)
            val parentVideoAdapter = ListDateWithNoteAdapter()
            parentVideoAdapter.notifyDataSetChanged()
            parentVideoAdapter.setData(dateNoteList)


            binding.rvDashboard.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.rvDashboard.adapter = parentVideoAdapter
        } else {
            binding.rvDashboard.visibility = View.INVISIBLE
            binding.ivEmptyDashboard.visibility = View.VISIBLE
            binding.tvEmptyDashboard.visibility = View.VISIBLE
            binding.btnAddNote.visibility = View.VISIBLE
        }
    }

}