package com.fakhry.lifelog.core.database.model

@Deprecated("This is should be a domain class. Use the one on shared domain module.")
data class DateNoteEntity(
    val date : String,
    val listNote : List<NoteEntity>
)