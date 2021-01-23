package com.fakhry.lifelog.data.local.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "edit_log_entity")
class EditLogEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_edit")
    var id_edit: Int,

    @ColumnInfo(name = "date_created")
    var date_created: String,

    @ColumnInfo(name = "edit_description")
    var edit_description: String,
)