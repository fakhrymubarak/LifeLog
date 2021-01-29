package com.fakhry.lifelog.ui.fragments.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fakhry.lifelog.base.BaseFunction
import com.fakhry.lifelog.data.local.entities.NoteEntity
import com.fakhry.lifelog.databinding.FragmentCalendarBinding
import com.fakhry.lifelog.ui.adapters.ListNoteAdapter
import com.fakhry.lifelog.viewmodel.ViewModelFactory

class CalendarFragment : Fragment() {

    private lateinit var calendarViewModel: CalendarViewModel
    private lateinit var binding: FragmentCalendarBinding
    private lateinit var baseFunction: BaseFunction

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseFunction = BaseFunction(requireContext())

        val dateRightNow = baseFunction.getFormalDate(withHours = false)
        binding.tvSelectedDate.text = dateRightNow

        val factory = ViewModelFactory.getInstance(requireContext())
        calendarViewModel = ViewModelProvider(this, factory)[CalendarViewModel::class.java]
        setViewModel(dateRightNow)

        binding.calendar.setOnDateChangeListener { _, year, month, date ->
            populateView(date, month + 1, year)
        }
    }

    private fun populateView(date: Int, month: Int, year: Int) {
        val completeDate = "$date/${month}/$year,00:00"
        val dateSelected = baseFunction.dateToFormalString(completeDate, false)
        binding.tvSelectedDate.text = dateSelected
        setViewModel(dateSelected)
    }

    private fun setViewModel(dateSelected: String) {
        calendarViewModel.getNoteBasedDate(dateSelected).observe(viewLifecycleOwner, { listNote ->
            if (!listNote.isNullOrEmpty()) {
                binding.ivIllustNothing.visibility = View.GONE
                binding.tvNothing.visibility = View.GONE
                binding.rvNoteCalendar.visibility = View.VISIBLE
                setRecyclerViewNote(listNote)
            } else {
                binding.ivIllustNothing.visibility = View.VISIBLE
                binding.tvNothing.visibility = View.VISIBLE
                binding.rvNoteCalendar.visibility = View.GONE
            }
        })
    }

    private fun setRecyclerViewNote(listNote: List<NoteEntity>) {
        binding.rvNoteCalendar.setHasFixedSize(true)
        val childNoteAdapter = ListNoteAdapter()
        childNoteAdapter.notifyDataSetChanged()
        childNoteAdapter.setData(listNote)

        binding.rvNoteCalendar.layoutManager =
            LinearLayoutManager(binding.rvNoteCalendar.context, LinearLayoutManager.VERTICAL, false)
        binding.rvNoteCalendar.adapter = childNoteAdapter
    }
}