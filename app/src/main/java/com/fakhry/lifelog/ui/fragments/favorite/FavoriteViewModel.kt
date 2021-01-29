package com.fakhry.lifelog.ui.fragments.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.data.Repository
import com.fakhry.lifelog.data.local.entities.NoteEntity
import kotlinx.coroutines.launch

class FavoriteViewModel(private val mRepository: Repository) : ViewModel() {

    fun getFavoriteNote(): LiveData<List<NoteEntity>> {
        val listNotes = MutableLiveData<List<NoteEntity>>()
        viewModelScope.launch {
            listNotes.postValue(mRepository.getNotesBasedFavorite())
        }
        return listNotes
    }
}