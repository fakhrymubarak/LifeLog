package com.fakhry.lifelog.ui.activities.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.data.Repository
import com.fakhry.lifelog.data.local.entities.EditLogEntity
import com.fakhry.lifelog.data.local.entities.NoteEntity
import com.fakhry.lifelog.data.local.entities.TagEntity
import com.fakhry.lifelog.data.local.relation.NoteTagCrossRef
import com.fakhry.lifelog.data.local.relation.NoteWithEditLogsRelation
import kotlinx.coroutines.launch

class AddUpdateViewModel(private val mRepository: Repository) : ViewModel() {
    fun insertNote(note: NoteEntity) {
        viewModelScope.launch {
            mRepository.insertNote(note)
        }
    }

//    fun getNoteDetailsWithTag(noteDateCreated: Long): LiveData<NoteWithTagRelation> {
//        val note = MutableLiveData<NoteWithTagRelation>()
//        viewModelScope.launch {
//            note.postValue(mRepository.getNotesWithTags(noteDateCreated))
//        }
//        return note
//    }

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