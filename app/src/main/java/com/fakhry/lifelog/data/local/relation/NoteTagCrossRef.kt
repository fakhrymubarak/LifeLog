package com.fakhry.lifelog.data.local.relation

import androidx.room.Entity

@Entity(tableName = "note_tags_entity", primaryKeys = ["idNote", "idTag"])
data class NoteTagCrossRef(
    val idNote : Int,
    val idTag : Int
)