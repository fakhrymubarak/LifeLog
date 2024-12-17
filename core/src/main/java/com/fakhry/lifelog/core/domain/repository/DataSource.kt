package com.fakhry.lifelog.core.domain.repository

import com.fakhry.lifelog.storage.model.EditLogEntity
import com.fakhry.lifelog.storage.model.NoteEntity
import com.fakhry.lifelog.storage.model.TagEntity
import com.fakhry.lifelog.storage.model.relation.NoteTagCrossRef
import com.fakhry.lifelog.storage.model.relation.NoteWithEditLogsRelation
import com.fakhry.lifelog.storage.model.relation.NoteWithTagRelation
import com.fakhry.lifelog.storage.model.relation.TagWithNoteRelation

interface DataSource {
    /*INSERT METHOD*/
    suspend fun insertNote(note: NoteEntity)
    suspend fun insertEdit(editLog: EditLogEntity)
    suspend fun insertTag(tag: TagEntity)
    suspend fun insertNoteTagCrossRef(noteTagCrossRef: NoteTagCrossRef)

    /*GET METHOD*/
    suspend fun getAllDate(): List<String>
    suspend fun getNotesBasedDate(dateCreated: String): List<NoteEntity>
    suspend fun getNotesBasedFavorite(): List<NoteEntity>
    suspend fun getNoteDetails(noteCreatedDate: Long): NoteEntity
    suspend fun getNoteWithEditLogs(noteCreatedDate: Long): NoteWithEditLogsRelation

    suspend fun getNotesWithTags(noteCreatedDate: Long): NoteWithTagRelation
    suspend fun getTagsWithNotes(tagName: String): TagWithNoteRelation

    /*UPDATE METHOD*/
    suspend fun updateSelectedNote(note: NoteEntity)

    /*DELETE METHOD*/
    suspend fun delSelectedNote(note: NoteEntity)
}