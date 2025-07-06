package com.fakhry.lifelog.details.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.commons.data.local.LocalDataRepository
import com.fakhry.lifelog.core.database.model.EditLogEntity
import com.fakhry.lifelog.core.database.model.NoteEntity
import com.fakhry.lifelog.core.database.model.TagEntity
import com.fakhry.lifelog.core.database.model.relation.NoteTagCrossRef
import com.fakhry.lifelog.core.database.model.relation.NoteWithEditLogsRelation
import kotlinx.coroutines.launch

class AddUpdateViewModel(private val mRepository: LocalDataRepository) : ViewModel() {
    fun insertNote(note: NoteEntity) {
        viewModelScope.launch {
            mRepository.insertNote(note)
        }
    }

    fun insertTag(tag: TagEntity) {
        viewModelScope.launch {
            mRepository.insertTag(tag)
        }
    }

    fun insertEditLog(editLog: EditLogEntity) {
        viewModelScope.launch {
            mRepository.insertEdit(editLog)
        }
    }

    fun insertNoteTagCrossRef(noteTagCrossRef: NoteTagCrossRef) {
        viewModelScope.launch {
            mRepository.insertNoteTagCrossRef(noteTagCrossRef)
        }
    }

    fun getNoteWithEditLogs(idNote: Long): LiveData<NoteWithEditLogsRelation> {
        val noteWithEdit = MutableLiveData<NoteWithEditLogsRelation>()
        viewModelScope.launch {
            noteWithEdit.postValue(mRepository.getNoteWithEditLogs(idNote))
        }
        return noteWithEdit
    }
}