package com.fakhry.lifelog.data.local.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tag_entity")
data class TagEntity(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "tag_name", index = true)
    var tagName: String,

    //Relation n-to-n
    @ColumnInfo(name = "note_created_date")
    val noteCreatedDate: Long
)