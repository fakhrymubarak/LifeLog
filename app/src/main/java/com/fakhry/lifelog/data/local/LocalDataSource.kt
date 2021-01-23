package com.fakhry.lifelog.data.local

import com.fakhry.lifelog.data.local.entities.EditLogEntity
import com.fakhry.lifelog.data.local.entities.NoteEntity
import com.fakhry.lifelog.data.local.relation.NoteWithEditLogs
import com.fakhry.lifelog.data.local.room.LifeLogDao

class LocalDataSource private constructor(private val mLifeLogDao: LifeLogDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(lifeLogDao: LifeLogDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(lifeLogDao)
            }
            return INSTANCE as LocalDataSource
        }
    }

    /*INSERT METHOD*/

    suspend fun insertNote(note: NoteEntity) = mLifeLogDao.insertNote(note)
    suspend fun insertEdit(editLog: EditLogEntity) = mLifeLogDao.insertEdit(editLog)

    /*GET METHOD*/
    suspend fun getAllDate(): List<String> = mLifeLogDao.getAllDate()
    suspend fun getNotesBasedDate(dateCreated: String): List<NoteEntity> = mLifeLogDao.getNotesBasedDate(dateCreated)
    suspend fun getNotesBasedFavorite(): List<NoteEntity> = mLifeLogDao.getNotesBasedFavorite()
    suspend fun getNoteDetails(idNote: Int): NoteEntity = mLifeLogDao.getNoteDetails(idNote)
    suspend fun getNoteWithEditLogs(idNote: Int): List<NoteWithEditLogs> = mLifeLogDao.getNoteWithEditLogs(idNote)

    /*UPDATE METHOD*/
    suspend fun updateSelectedNote(idNote: Int) = mLifeLogDao.updateSelectedNote(idNote)

    /*DELETE METHOD*/
    suspend fun delSelectedNote(idNote: Int) = mLifeLogDao.delSelectedNote(idNote)
}