package com.fakhry.lifelog.core.database.model.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.fakhry.lifelog.core.database.model.NoteEntity
import com.fakhry.lifelog.core.database.model.TagEntity

data class TagWithNoteRelation(
    @Embedded
    val tags: TagEntity,

    @Relation(
        parentColumn = "tag_name",
        entityColumn = "note_created_date",
        associateBy = Junction(NoteTagCrossRef::class)
    )
    val note: List<NoteEntity>
)