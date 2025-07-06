package com.fakhry.lifelog.domain.repository

import com.fakhry.lifelog.domain.model.EditLogDomain
import com.fakhry.lifelog.domain.model.NoteDomain
import com.fakhry.lifelog.domain.model.TagDomain
import com.fakhry.lifelog.domain.model.relation.NoteTagDomain
import com.fakhry.lifelog.domain.model.relation.NoteWithEditLogsDomain
import com.fakhry.lifelog.domain.model.relation.NoteWithTagDomain
import com.fakhry.lifelog.domain.model.relation.TagWithNoteDomain

interface NoteLocalRepository {

    // INSERT
    suspend fun insertNote(note: NoteDomain)
    suspend fun insertEdit(editLog: EditLogDomain)
    suspend fun insertTag(tag: TagDomain)
    suspend fun insertNoteTagCrossRef(noteTagCrossRef: NoteTagDomain)

    // GET
    suspend fun getAllDate(): List<String>
    suspend fun getNotesBasedDate(dateCreated: String): List<NoteDomain>
    suspend fun getNotesBasedFavorite(): List<NoteDomain>
    suspend fun getNoteDetails(noteCreatedDate: Long): NoteDomain
    suspend fun getNoteWithEditLogs(noteCreatedDate: Long): NoteWithEditLogsDomain
    suspend fun getNotesWithTags(noteCreatedDate: Long): NoteWithTagDomain
    suspend fun getTagsWithNote(tagName: String): TagWithNoteDomain

    // UPDATE
    suspend fun updateSelectedNote(note: NoteDomain)

    // DELETE
    suspend fun delSelectedNote(note: NoteDomain)
}