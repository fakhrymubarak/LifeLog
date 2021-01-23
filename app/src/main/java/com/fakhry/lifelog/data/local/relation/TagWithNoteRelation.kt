package com.fakhry.lifelog.data.local.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.fakhry.lifelog.data.local.entities.NoteEntity
import com.fakhry.lifelog.data.local.entities.TagEntity

data class TagWithNoteRelation(
    @Embedded
    val tags: TagEntity,

    @Relation(
        parentColumn = "id_tag",
        entityColumn = "id_note",
        associateBy = Junction(NoteTagCrossRef::class)
    )
    val note: List<NoteEntity>
)
