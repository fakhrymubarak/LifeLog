package com.fakhry.lifelog.commons.data.local

import com.fakhry.lifelog.core.database.model.EditLogEntity
import com.fakhry.lifelog.core.database.model.NoteEntity
import com.fakhry.lifelog.core.database.model.TagEntity
import com.fakhry.lifelog.core.database.model.relation.NoteTagCrossRef
import com.fakhry.lifelog.core.database.model.relation.NoteWithEditLogsRelation
import com.fakhry.lifelog.core.database.model.relation.NoteWithTagRelation
import com.fakhry.lifelog.core.database.model.relation.TagWithNoteRelation

interface LocalDataRepository {
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