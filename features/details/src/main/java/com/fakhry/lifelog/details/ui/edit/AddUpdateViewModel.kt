package com.fakhry.lifelog.details.ui.edit

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.core.data.repository.Repository
import com.fakhry.lifelog.core.domain.repository.DataSource
import com.fakhry.lifelog.storage.model.EditLogEntity
import com.fakhry.lifelog.storage.model.NoteEntity
import com.fakhry.lifelog.storage.model.TagEntity
import com.fakhry.lifelog.storage.model.relation.NoteTagCrossRef
import com.fakhry.lifelog.storage.model.relation.NoteWithEditLogsRelation
import com.fakhry.lifelog.storage.room.LifeLogDatabase
import com.fakhry.lifelog.storage.room.LocalDataSource
import kotlinx.coroutines.launch

class AddUpdateViewModel(private val mRepository: DataSource) : ViewModel() {
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

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory {
            return object : ViewModelProvider.NewInstanceFactory() {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val database = LifeLogDatabase.getInstance(context)
                    val localDataSource = LocalDataSource.getInstance(database.lifeLogDao())
                    val repository = Repository.getInstance(localDataSource)

                    if (modelClass.isAssignableFrom(AddUpdateViewModel::class.java)) {
                        @Suppress("UNCHECKED_CAST")
                        return AddUpdateViewModel(repository) as T
                    } else {
                        throw IllegalArgumentException("Unknown ViewModel class")
                    }
                }
            }
        }
    }
}