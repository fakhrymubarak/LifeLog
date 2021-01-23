package com.fakhry.lifelog.data

import com.fakhry.lifelog.data.local.LocalDataSource
import com.fakhry.lifelog.data.local.entities.EditLogEntity
import com.fakhry.lifelog.data.local.entities.NoteEntity
import com.fakhry.lifelog.data.local.relation.NoteWithEditLogs

class Repository(private val mLocalDataSource: LocalDataSource) : DataSource {
    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            localDataSource: LocalDataSource,
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(localDataSource)
            }
    }

    override suspend fun insertNote(note: NoteEntity) = mLocalDataSource.insertNote(note)
    override suspend fun insertEdit(editLog: EditLogEntity) = mLocalDataSource.insertEdit(editLog)

    /*GET METHOD*/
    override suspend fun getAllDate(): List<String> = mLocalDataSource.getAllDate()
    override suspend fun getNotesBasedDate(dateCreated: String): List<NoteEntity> =
        mLocalDataSource.getNotesBasedDate(dateCreated)

    override suspend fun getNotesBasedFavorite(): List<NoteEntity> =
        mLocalDataSource.getNotesBasedFavorite()

    override suspend fun getNoteDetails(idNote: Int): NoteEntity =
        mLocalDataSource.getNoteDetails(idNote)

    override suspend fun getNoteWithEditLogs(idNote: Int): List<NoteWithEditLogs> =
        mLocalDataSource.getNoteWithEditLogs(idNote)

    /*UPDATE METHOD*/
    override suspend fun updateSelectedNote(idNote: Int) =
        mLocalDataSource.updateSelectedNote(idNote)

    /*DELETE METHOD*/
    override suspend fun delSelectedNote(idNote: Int) = mLocalDataSource.delSelectedNote(idNote)
}