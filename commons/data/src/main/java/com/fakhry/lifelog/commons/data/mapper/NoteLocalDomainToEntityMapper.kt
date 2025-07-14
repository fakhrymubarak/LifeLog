package com.fakhry.lifelog.commons.data.mapper

import com.fakhry.lifelog.core.database.model.EditLogEntity
import com.fakhry.lifelog.core.database.model.NoteEntity
import com.fakhry.lifelog.core.database.model.TagEntity
import com.fakhry.lifelog.core.database.model.relation.NoteTagCrossRef
import com.fakhry.lifelog.domain.model.EditLogDomain
import com.fakhry.lifelog.domain.model.NoteDomain
import com.fakhry.lifelog.domain.model.TagDomain
import com.fakhry.lifelog.domain.model.relation.NoteTagDomain

class NoteLocalDomainToEntityMapper {
    fun mapNoteDomainToEntity(note: NoteDomain) = NoteEntity(
        noteCreatedDate = note.noteCreatedDate,
        createdDate = note.createdDate,
        title = note.title,
        description = note.description,
        moodIndicator = note.moodIndicator,
        isFavNote = note.isFavNote,
        lastUpdate = note.lastUpdate,
    )

    fun mapEditLogDomainToEntity(editLog: EditLogDomain) = EditLogEntity(
        noteCreatedDate = editLog.noteCreatedDate,
        noteEditDate = editLog.noteEditDate,
        editDescription = editLog.editDescription,
    )

    fun mapTagDomainToEntity(tag: TagDomain) = TagEntity(
        tagName = tag.tagName,
        noteCreatedDate = tag.noteCreatedDate,
    )

    fun mapNoteTagDomainToEntity(noteTag: NoteTagDomain) = NoteTagCrossRef(
        noteCreatedDate = noteTag.noteCreatedDate,
        tagName = noteTag.tagName,
    )
}