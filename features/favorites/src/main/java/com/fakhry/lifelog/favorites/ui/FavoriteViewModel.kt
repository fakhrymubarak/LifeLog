package com.fakhry.lifelog.favorites.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.commons.data.local.LocalDataRepository
import com.fakhry.lifelog.core.database.model.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(private val mRepository: LocalDataRepository) : ViewModel() {

    fun getFavoriteNote(): LiveData<List<NoteEntity>> {
        val listNotes = MutableLiveData<List<NoteEntity>>()
        viewModelScope.launch(Dispatchers.IO) {
            listNotes.postValue(mRepository.getNotesBasedFavorite())
        }
        return listNotes
    }
}