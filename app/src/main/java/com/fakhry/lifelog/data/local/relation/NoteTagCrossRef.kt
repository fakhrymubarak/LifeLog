package com.fakhry.lifelog.data.local.relation

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "note_tag_cross_ref", primaryKeys = ["note_created_date", "tag_name"])
data class NoteTagCrossRef(
    @ColumnInfo(name = "note_created_date", index = true)
    var noteCreatedDate: Long,

    @ColumnInfo(name = "tag_name", index = true)
    var tagName: String
)