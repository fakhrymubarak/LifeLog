package com.fakhry.lifelog.data.local.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "edit_log_entity")
data class EditLogEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_edit")
    var idEdit: Int,

    @ColumnInfo(name = "date_created")
    var dateCreated: String,

    @ColumnInfo(name = "edit_description")
    var editDescription: String,

    //Relation 1-to-n
    val idNote: Int
)