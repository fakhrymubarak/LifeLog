package com.fakhry.lifelog.dashboard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhry.lifelog.components.adapters.ListDateWithNoteAdapter
import com.fakhry.lifelog.dashboard.databinding.FragmentDashboardBinding
import com.fakhry.lifelog.navigation.Router
import com.fakhry.lifelog.storage.model.DateNoteEntity

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var binding: FragmentDashboardBinding

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

        val factory = context?.let { DashboardViewModel.provideFactory(it) } ?: return
        dashboardViewModel = ViewModelProvider(this, factory)[DashboardViewModel::class.java]

        getNoteBasedDate()
        binding.btnAddNote.setOnClickListener {
            context?.let { Router.navigateToEdit(it) }
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