package com.fakhry.lifelog.core.database.room

import com.fakhry.lifelog.core.database.model.EditLogEntity
import com.fakhry.lifelog.core.database.model.NoteEntity
import com.fakhry.lifelog.core.database.model.TagEntity
import com.fakhry.lifelog.core.database.model.relation.NoteTagCrossRef
import com.fakhry.lifelog.core.database.model.relation.NoteWithEditLogsRelation
import com.fakhry.lifelog.core.database.model.relation.NoteWithTagRelation
import com.fakhry.lifelog.core.database.model.relation.TagWithNoteRelation

class LocalDataSource(private val mLifeLogDao: LifeLogDao) {
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

    suspend fun getNotesWithTags(noteCreatedDate: Long): NoteWithTagRelation =
        mLifeLogDao.getNotesWithTags(noteCreatedDate)

    suspend fun getTagsWithNote(tagName: String): TagWithNoteRelation =
        mLifeLogDao.getTagsWithNotes(tagName)

    /*UPDATE METHOD*/
    suspend fun updateSelectedNote(note: NoteEntity) = mLifeLogDao.updateSelectedNote(note)

    /*DELETE METHOD*/
    suspend fun delSelectedNote(note: NoteEntity) = mLifeLogDao.delSelectedNote(note)
}