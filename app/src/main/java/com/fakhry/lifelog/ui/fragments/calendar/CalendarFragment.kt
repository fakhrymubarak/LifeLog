package com.fakhry.lifelog.ui.fragments.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fakhry.lifelog.base.BaseFunction
import com.fakhry.lifelog.databinding.FragmentCalendarBinding

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

        val timeMillis = System.currentTimeMillis().toString()
        val formalDate = baseFunction.epochToDate(timeMillis)
        val arrDate = formalDate.split("/")
        updateNoteList(arrDate[0].toInt(), arrDate[1].toInt(), arrDate[2].toInt())

        binding.calendar.setOnDateChangeListener { _, year, month, date ->
            updateNoteList(date, month + 1, year)
        }
    }

    private fun updateNoteList(date: Int, month: Int, year: Int) {
        val completeDate = "$date/${month}/$year"
        binding.tvSelectedDate.text = baseFunction.dateToFormalString(completeDate)
    }
}