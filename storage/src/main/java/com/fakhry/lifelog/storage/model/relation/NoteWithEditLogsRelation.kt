package com.fakhry.lifelog.storage.model.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.fakhry.lifelog.storage.model.EditLogEntity
import com.fakhry.lifelog.storage.model.NoteEntity

data class NoteWithEditLogsRelation(
    @Embedded
    val note: NoteEntity,

    @Relation(
        parentColumn = "note_created_date",
        entityColumn = "note_created_date"
    )
    val listEditLogEntity: List<EditLogEntity>
)