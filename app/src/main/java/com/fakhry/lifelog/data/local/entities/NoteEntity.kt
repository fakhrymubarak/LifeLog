package com.fakhry.lifelog.data.local.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_entity")
data class NoteEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_note")
    var noteId: Int,

    @ColumnInfo(name = "date_created")
    var date_created: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "is_fav_note")
    var isFavNote: Boolean,

    @ColumnInfo(name = "last_updatet")
    var lastUpdate: String
)
