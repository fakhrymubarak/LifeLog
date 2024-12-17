package com.fakhry.lifelog.storage.model

data class DateNoteEntity(
    val date : String,
    val listNote : List<NoteEntity>
)