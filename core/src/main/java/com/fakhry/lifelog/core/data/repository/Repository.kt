package com.fakhry.lifelog.core.data.repository

import com.fakhry.lifelog.core.domain.repository.DataSource
import com.fakhry.lifelog.storage.room.LocalDataSource
import com.fakhry.lifelog.storage.model.EditLogEntity
import com.fakhry.lifelog.storage.model.NoteEntity
import com.fakhry.lifelog.storage.model.TagEntity
import com.fakhry.lifelog.storage.model.relation.NoteTagCrossRef
import com.fakhry.lifelog.storage.model.relation.NoteWithEditLogsRelation
import com.fakhry.lifelog.storage.model.relation.NoteWithTagRelation
import com.fakhry.lifelog.storage.model.relation.TagWithNoteRelation

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