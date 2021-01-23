package com.fakhry.lifelog.ui.fragments.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fakhry.lifelog.data.Repository

class CalendarViewModel(mRepository: Repository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is calendar Fragment"
    }
    val text: LiveData<String> = _text
}