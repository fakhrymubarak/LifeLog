package com.fakhry.lifelog.ui.activities.read

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.core.data.repository.Repository
import com.fakhry.lifelog.storage.model.NoteEntity
import com.fakhry.lifelog.storage.model.relation.NoteWithEditLogsRelation
import com.fakhry.lifelog.storage.model.relation.NoteWithTagRelation
import kotlinx.coroutines.launch

class ReadViewModel(private val mRepository: Repository) : ViewModel() {
    fun getNoteDetailsWithEdit(noteDateCreated: Long): LiveData<NoteWithEditLogsRelation> {
        val note = MutableLiveData<NoteWithEditLogsRelation>()
        viewModelScope.launch {
            note.postValue(mRepository.getNoteWithEditLogs(noteDateCreated))
        }
        return note
    }

    fun getNoteDetailsWithTag(noteDateCreated: Long): LiveData<NoteWithTagRelation> {
        val note = MutableLiveData<NoteWithTagRelation>()
        viewModelScope.launch {
            note.postValue(mRepository.getNotesWithTags(noteDateCreated))
        }
        return note
    }

    fun deleteNote(noteEntity: NoteEntity) {
        viewModelScope.launch {
            mRepository.delSelectedNote(noteEntity)
        }
    }

    fun favNote(noteEntity: NoteEntity) {
        viewModelScope.launch {
            mRepository.updateSelectedNote(noteEntity)
        }
    }
}