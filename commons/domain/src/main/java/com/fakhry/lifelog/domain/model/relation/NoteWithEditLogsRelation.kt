package com.fakhry.lifelog.domain.model.relation

import com.fakhry.lifelog.domain.model.EditLogDomain
import com.fakhry.lifelog.domain.model.NoteDomain

data class NoteWithEditLogsDomain(
    val note: NoteDomain,
    val listEditLogEntity: List<EditLogDomain>,
)