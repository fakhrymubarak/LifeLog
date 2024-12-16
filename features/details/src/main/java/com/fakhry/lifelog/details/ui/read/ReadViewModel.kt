package com.fakhry.lifelog.details.ui.read

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.core.data.repository.Repository
import com.fakhry.lifelog.core.domain.repository.DataSource
import com.fakhry.lifelog.storage.model.NoteEntity
import com.fakhry.lifelog.storage.model.relation.NoteWithEditLogsRelation
import com.fakhry.lifelog.storage.model.relation.NoteWithTagRelation
import com.fakhry.lifelog.storage.room.LifeLogDatabase
import com.fakhry.lifelog.storage.room.LocalDataSource
import kotlinx.coroutines.launch

class ReadViewModel(private val mRepository: DataSource) : ViewModel() {
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

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory {
            return object : ViewModelProvider.NewInstanceFactory() {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val database = LifeLogDatabase.getInstance(context)
                    val localDataSource = LocalDataSource.getInstance(database.lifeLogDao())
                    val repository = Repository.getInstance(localDataSource)

                    if (modelClass.isAssignableFrom(ReadViewModel::class.java)) {
                        @Suppress("UNCHECKED_CAST")
                        return ReadViewModel(repository) as T
                    } else {
                        throw IllegalArgumentException("Unknown ViewModel class")
                    }
                }
            }
        }
    }
}