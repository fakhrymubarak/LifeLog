package com.fakhry.lifelog.domain.model.relation

import com.fakhry.lifelog.domain.model.NoteDomain
import com.fakhry.lifelog.domain.model.TagDomain

data class NoteWithTagDomain(
    val note: NoteDomain,
    val tags: List<TagDomain>
)