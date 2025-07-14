package com.fakhry.lifelog.domain.model

data class EditLogDomain(
    val noteEditDate: Long = 0L,
    val editDescription: String = "",
    val noteCreatedDate: Long = 0L
)