package com.fakhry.lifelog.data.local.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "edit_log_entity")
data class EditLogEntity(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "note_edit_date", index = true)
    val noteEditDate: Long,

    @ColumnInfo(name = "edit_description")
    val editDescription: String,

    //Relation 1-to-n
    @ColumnInfo(name = "note_created_date")
    val noteCreatedDate: Long
)