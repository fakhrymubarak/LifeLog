package com.fakhry.lifelog.domain.model

data class DateNoteDomain(
    val date : String = "",
    val listNote : List<NoteDomain> = emptyList()
)
