package com.fakhry.lifelog.domain.model

data class DateNoteDomain(
    val date : String,
    val listNote : List<NoteDomain>
)

data class NoteDomain(
    val noteCreatedDate: Long,
    val createdDate: String,
    val title: String,
    val description: String,
    val moodIndicator: Int,
    val isFavNote: Boolean,
    val lastUpdate: Long
)