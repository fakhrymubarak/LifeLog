package com.fakhry.lifelog.core.database.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.fakhry.lifelog.core.database.model.EditLogEntity
import com.fakhry.lifelog.core.database.model.NoteEntity

data class NoteWithEditLogsRelation(
    @Embedded
    val note: NoteEntity,

    @Relation(
        parentColumn = "note_created_date",
        entityColumn = "note_created_date"
    )
    val listEditLogEntity: List<EditLogEntity>
)