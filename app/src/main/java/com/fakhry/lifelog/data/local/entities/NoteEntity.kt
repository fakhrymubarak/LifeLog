package com.fakhry.lifelog.data.local.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_entity")
data class NoteEntity(
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "note_created_date", index = true)
    var noteCreatedDate: Long,

    @ColumnInfo(name = "created_date")
    var createdDate: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "is_fav_note")
    var isFavNote: Boolean,

    @ColumnInfo(name = "last_update")
    var lastUpdate: Long
)
