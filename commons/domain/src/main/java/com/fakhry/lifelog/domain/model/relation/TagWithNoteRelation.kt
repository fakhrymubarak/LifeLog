package com.fakhry.lifelog.domain.model.relation

import com.fakhry.lifelog.domain.model.NoteDomain
import com.fakhry.lifelog.domain.model.TagDomain

data class TagWithNoteDomain(
    val tags: TagDomain,
    val note: List<NoteDomain>
)