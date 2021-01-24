package com.fakhry.lifelog.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.fakhry.lifelog.data.local.entities.EditLogEntity
import com.fakhry.lifelog.data.local.entities.NoteEntity

data class NoteWithEditLogsRelation(
    @Embedded
    val note: NoteEntity,

    @Relation(
        parentColumn = "note_created_date",
        entityColumn = "note_created_date"
    )
    val listEditLogEntity: List<EditLogEntity>
)