package com.fakhry.lifelog.commons.data.local

import com.fakhry.lifelog.core.database.model.EditLogEntity
import com.fakhry.lifelog.core.database.model.NoteEntity
import com.fakhry.lifelog.core.database.model.TagEntity
import com.fakhry.lifelog.core.database.model.relation.NoteTagCrossRef
import com.fakhry.lifelog.core.database.model.relation.NoteWithEditLogsRelation
import com.fakhry.lifelog.core.database.model.relation.NoteWithTagRelation
import com.fakhry.lifelog.core.database.model.relation.TagWithNoteRelation
import com.fakhry.lifelog.core.database.room.LocalDataSource

class LocalDataRepositoryImpl(private val mLocalDataSource: LocalDataSource) : LocalDataRepository {

    override suspend fun insertNote(note: NoteEntity) = mLocalDataSource.insertNote(note)
    override suspend fun insertEdit(editLog: EditLogEntity) = mLocalDataSource.insertEdit(editLog)
    override suspend fun insertTag(tag: TagEntity) = mLocalDataSource.insertTag(tag)
    override suspend fun insertNoteTagCrossRef(noteTagCrossRef: NoteTagCrossRef) =
        mLocalDataSource.insertNoteTagCrossRef(noteTagCrossRef)

    /*GET METHOD*/
    override suspend fun getAllDate(): List<String> = mLocalDataSource.getAllDate()
    override suspend fun getNotesBasedDate(dateCreated: String): List<NoteEntity> =
        mLocalDataSource.getNotesBasedDate(dateCreated)

    override suspend fun getNotesBasedFavorite(): List<NoteEntity> =
        mLocalDataSource.getNotesBasedFavorite()

    override suspend fun getNoteDetails(noteCreatedDate: Long): NoteEntity =
        mLocalDataSource.getNoteDetails(noteCreatedDate)

    override suspend fun getNoteWithEditLogs(noteCreatedDate: Long): NoteWithEditLogsRelation =
        mLocalDataSource.getNoteWithEditLogs(noteCreatedDate)

    override suspend fun getNotesWithTags(noteCreatedDate: Long): NoteWithTagRelation =
        mLocalDataSource.getNotesWithTags(noteCreatedDate)

    override suspend fun getTagsWithNotes(tagName: String): TagWithNoteRelation =
        mLocalDataSource.getTagsWithNote(tagName)

    /*UPDATE METHOD*/
    override suspend fun updateSelectedNote(note: NoteEntity) =
        mLocalDataSource.updateSelectedNote(note)

    /*DELETE METHOD*/
    override suspend fun delSelectedNote(note: NoteEntity) = mLocalDataSource.delSelectedNote(note)
}