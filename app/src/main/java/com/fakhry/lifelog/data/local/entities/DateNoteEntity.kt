package com.fakhry.lifelog.data.local.entities

data class DateNoteEntity(
    val date : String,
    val listNote : List<NoteEntity>
)