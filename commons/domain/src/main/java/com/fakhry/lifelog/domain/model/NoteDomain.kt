package com.fakhry.lifelog.domain.model

data class NoteDomain(
    val noteCreatedDate: Long = 0L,
    val createdDate: String = "",
    val title: String = "",
    val description: String = "",
    val moodIndicator: Int = -1,
    val isFavNote: Boolean = false,
    val lastUpdate: Long = 0L,
)