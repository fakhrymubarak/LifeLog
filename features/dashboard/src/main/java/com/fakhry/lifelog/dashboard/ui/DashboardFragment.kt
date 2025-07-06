package com.fakhry.lifelog.dashboard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhry.lifelog.components.adapters.ListDateWithNoteAdapter
import com.fakhry.lifelog.core.database.model.DateNoteEntity
import com.fakhry.lifelog.dashboard.databinding.FragmentDashboardBinding
import com.fakhry.lifelog.dashboard.di.initDashboardFragmentKoinModules
import com.fakhry.lifelog.navigation.Router
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment(), AndroidScopeComponent {

    override val scope by fragmentScope()
    private val viewModel: DashboardViewModel by viewModel()

    private lateinit var binding: FragmentDashboardBinding

    init {
        initDashboardFragmentKoinModules()
    }

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

        getNoteBasedDate()
        binding.btnAddNote.setOnClickListener {
            context?.let { Router.navigateToEdit(it) }
        }
    }

    private fun getNoteBasedDate() {
        val listNoteDate = ArrayList<DateNoteEntity>()
        viewModel.getAllDates().observe(viewLifecycleOwner) { listDates ->
            listDates.forEach { date ->
                viewModel.getNoteBasedDate(date).observe(viewLifecycleOwner) { listNote ->
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