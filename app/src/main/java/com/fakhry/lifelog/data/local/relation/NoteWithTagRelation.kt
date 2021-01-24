package com.fakhry.lifelog.data.local.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.fakhry.lifelog.data.local.entities.NoteEntity
import com.fakhry.lifelog.data.local.entities.TagEntity

data class NoteWithTagRelation(
    @Embedded
    val note: NoteEntity,

    @Relation(
        parentColumn = "note_created_date",
        entityColumn = "tag_name",
        associateBy = Junction(NoteTagCrossRef::class)
    )
    val tags: List<TagEntity>
)