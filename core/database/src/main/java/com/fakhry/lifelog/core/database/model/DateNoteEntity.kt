package com.fakhry.lifelog.core.database.model

data class DateNoteEntity(
    val date : String,
    val listNote : List<NoteEntity>
)