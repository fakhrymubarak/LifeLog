package com.fakhry.lifelog.details.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.domain.model.EditLogDomain
import com.fakhry.lifelog.domain.model.NoteDomain
import com.fakhry.lifelog.domain.model.TagDomain
import com.fakhry.lifelog.domain.model.relation.NoteTagDomain
import com.fakhry.lifelog.domain.model.relation.NoteWithEditLogsDomain
import com.fakhry.lifelog.domain.repository.NoteLocalRepository
import kotlinx.coroutines.launch

class AddUpdateViewModel(private val repos: NoteLocalRepository) : ViewModel() {
    fun insertNote(note: NoteDomain) {
        viewModelScope.launch {
            repos.insertNote(note)
        }
    }

    fun insertTag(tag: TagDomain) {
        viewModelScope.launch {
            repos.insertTag(tag)
        }
    }

    fun insertEditLog(editLog: EditLogDomain) {
        viewModelScope.launch {
            repos.insertEdit(editLog)
        }
    }

    fun insertNoteTag(noteTagDomain: NoteTagDomain) {
        viewModelScope.launch {
            repos.insertNoteTagCrossRef(noteTagDomain)
        }
    }

    fun getNoteWithEditLogs(idNote: Long): LiveData<NoteWithEditLogsDomain> {
        val noteWithEdit = MutableLiveData<NoteWithEditLogsDomain>()
        viewModelScope.launch {
            noteWithEdit.postValue(repos.getNoteWithEditLogs(idNote))
        }
        return noteWithEdit
    }
}