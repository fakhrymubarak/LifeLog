package com.fakhry.lifelog.data.local.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tag_entity")
data class TagEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_tag")
    var idTag: Int,

    @ColumnInfo(name = "tag_name")
    var tagName: String,

    //Relation n-to-n
    val idNote: Int
)