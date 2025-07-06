package com.fakhry.lifelog.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_entity")
data class NoteEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "note_created_date", index = true)
    val noteCreatedDate: Long,

    @ColumnInfo(name = "created_date")
    val createdDate: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "mood_indicator")
    val moodIndicator: Int,

    @ColumnInfo(name = "is_fav_note")
    val isFavNote: Boolean,

    @ColumnInfo(name = "last_update")
    val lastUpdate: Long
)
