package com.fakhry.lifelog.details.ui.read

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.domain.model.NoteDomain
import com.fakhry.lifelog.domain.model.relation.NoteWithEditLogsDomain
import com.fakhry.lifelog.domain.model.relation.NoteWithTagDomain
import com.fakhry.lifelog.domain.repository.NoteLocalRepository
import kotlinx.coroutines.launch

class ReadViewModel(private val repos: NoteLocalRepository) : ViewModel() {
    fun getNoteDetailsWithEdit(noteDateCreated: Long): LiveData<NoteWithEditLogsDomain> {
        val note = MutableLiveData<NoteWithEditLogsDomain>()
        viewModelScope.launch {
            note.postValue(repos.getNoteWithEditLogs(noteDateCreated))
        }
        return note
    }

    fun getNoteDetailsWithTag(noteDateCreated: Long): LiveData<NoteWithTagDomain> {
        val note = MutableLiveData<NoteWithTagDomain>()
        viewModelScope.launch {
            note.postValue(repos.getNotesWithTags(noteDateCreated))
        }
        return note
    }

    fun deleteNote(noteDomain: NoteDomain) {
        viewModelScope.launch {
            repos.delSelectedNote(noteDomain)
        }
    }

    fun favNote(noteDomain: NoteDomain) {
        viewModelScope.launch {
            repos.updateSelectedNote(noteDomain)
        }
    }
}