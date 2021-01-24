package com.fakhry.lifelog.data.local

import com.fakhry.lifelog.data.local.entities.EditLogEntity
import com.fakhry.lifelog.data.local.entities.NoteEntity
import com.fakhry.lifelog.data.local.entities.TagEntity
import com.fakhry.lifelog.data.local.relation.NoteTagCrossRef
import com.fakhry.lifelog.data.local.relation.NoteWithEditLogsRelation
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
    suspend fun insertTag(tag: TagEntity) = mLifeLogDao.insertTag(tag)
    suspend fun insertNoteTagCrossRef(noteTagCrossRef: NoteTagCrossRef) =
        mLifeLogDao.insertNoteTagCrossRef(noteTagCrossRef)

    /*GET METHOD*/
    suspend fun getAllDate(): List<String> = mLifeLogDao.getAllDates()

    suspend fun getNotesBasedDate(dateCreated: String): List<NoteEntity> =
        mLifeLogDao.getNotesBasedDate(dateCreated)

    suspend fun getNotesBasedFavorite(): List<NoteEntity> = mLifeLogDao.getNotesBasedFavorite()

    suspend fun getNoteDetails(noteCreatedDate: Long): NoteEntity =
        mLifeLogDao.getNoteDetails(noteCreatedDate)

    suspend fun getNoteWithEditLogs(noteCreatedDate: Long): NoteWithEditLogsRelation =
        mLifeLogDao.getNotesWithEditLogs(noteCreatedDate)

    /*UPDATE METHOD*/
    suspend fun updateSelectedNote(note: NoteEntity) = mLifeLogDao.updateSelectedNote(note)

    /*DELETE METHOD*/
    suspend fun delSelectedNote(note: NoteEntity) = mLifeLogDao.delSelectedNote(note)
}