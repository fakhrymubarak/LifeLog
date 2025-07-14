package com.fakhry.lifelog.commons.data.mapper

import com.fakhry.lifelog.core.database.model.EditLogEntity
import com.fakhry.lifelog.core.database.model.NoteEntity
import com.fakhry.lifelog.core.database.model.TagEntity
import com.fakhry.lifelog.core.database.model.relation.NoteWithEditLogsRelation
import com.fakhry.lifelog.core.database.model.relation.NoteWithTagRelation
import com.fakhry.lifelog.core.database.model.relation.TagWithNoteRelation
import com.fakhry.lifelog.domain.model.EditLogDomain
import com.fakhry.lifelog.domain.model.NoteDomain
import com.fakhry.lifelog.domain.model.TagDomain
import com.fakhry.lifelog.domain.model.relation.NoteWithEditLogsDomain
import com.fakhry.lifelog.domain.model.relation.NoteWithTagDomain
import com.fakhry.lifelog.domain.model.relation.TagWithNoteDomain

class NoteLocalEntityToDomainMapper {
    fun mapNotesEntityToDomain(notes: List<NoteEntity>) = notes.map {
        mapNoteEntityToDomain(it)
    }

    fun mapNoteEntityToDomain(note: NoteEntity) = NoteDomain(
        noteCreatedDate = note.noteCreatedDate,
        createdDate = note.createdDate,
        title = note.title,
        description = note.description,
        moodIndicator = note.moodIndicator,
        isFavNote = note.isFavNote,
        lastUpdate = note.lastUpdate,
    )

    fun mapNoteWithEditLogsEntityToDomain(result: NoteWithEditLogsRelation) =
        NoteWithEditLogsDomain(
            note = mapNoteEntityToDomain(result.note),
            listEditLogEntity = result.listEditLogEntity.map {
                mapEditLogEntityToDomain(it)
            }
        )

    private fun mapEditLogEntityToDomain(editLog: EditLogEntity) = EditLogDomain(
        noteCreatedDate = editLog.noteCreatedDate,
        noteEditDate = editLog.noteEditDate,
        editDescription = editLog.editDescription,
    )

    fun mapNoteWithTagEntityToDomain(result: NoteWithTagRelation) = NoteWithTagDomain(
        note = mapNoteEntityToDomain(result.note),
        tags = result.tags.map {
            mapTagEntityToDomain(it)
        }

    )

    private fun mapTagEntityToDomain(tag: TagEntity) = TagDomain(
        tagName = tag.tagName,
        noteCreatedDate = tag.noteCreatedDate,
    )

    fun mapTagWithNoteEntityToDomain(result: TagWithNoteRelation)= TagWithNoteDomain(
        tags = mapTagEntityToDomain(result.tags),
        note = result.note.map {
            mapNoteEntityToDomain(it)
        }
    )
}