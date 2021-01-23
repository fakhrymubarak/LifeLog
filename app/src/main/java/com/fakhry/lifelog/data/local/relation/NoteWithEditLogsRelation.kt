package com.fakhry.lifelog.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.fakhry.lifelog.data.local.entities.EditLogEntity
import com.fakhry.lifelog.data.local.entities.NoteEntity

data class NoteWithEditLogsRelation(
    @Embedded
    val note : NoteEntity,

    @Relation(
        parentColumn = "id_note",
        entityColumn = "id_note"
    )
    val listEditLogEntity : List<EditLogEntity>
)