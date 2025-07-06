package com.fakhry.lifelog.commons.data.repository

import com.fakhry.lifelog.commons.data.mapper.NoteLocalDomainToEntityMapper
import com.fakhry.lifelog.commons.data.mapper.NoteLocalEntityToDomainMapper
import com.fakhry.lifelog.core.database.room.LifeLogDao
import com.fakhry.lifelog.domain.model.EditLogDomain
import com.fakhry.lifelog.domain.model.NoteDomain
import com.fakhry.lifelog.domain.model.TagDomain
import com.fakhry.lifelog.domain.model.relation.NoteTagDomain
import com.fakhry.lifelog.domain.model.relation.NoteWithEditLogsDomain
import com.fakhry.lifelog.domain.model.relation.NoteWithTagDomain
import com.fakhry.lifelog.domain.model.relation.TagWithNoteDomain
import com.fakhry.lifelog.domain.repository.NoteLocalRepository

class NoteLocalRepositoryImpl(
    private val mLifeLogDao: LifeLogDao,
    private val mapperEntity: NoteLocalEntityToDomainMapper,
    private val mapperDomain: NoteLocalDomainToEntityMapper,
) : NoteLocalRepository {

    /*INSERT METHOD*/
    override suspend fun insertNote(note: NoteDomain) {
        mLifeLogDao.insertNote(mapperDomain.mapNoteDomainToEntity(note))
    }

    override suspend fun insertEdit(editLog: EditLogDomain) {
        mLifeLogDao.insertEdit(mapperDomain.mapEditLogDomainToEntity(editLog))
    }

    override suspend fun insertTag(tag: TagDomain) {
        mLifeLogDao.insertTag(mapperDomain.mapTagDomainToEntity(tag))
    }

    override suspend fun insertNoteTagCrossRef(noteTagCrossRef: NoteTagDomain) =
        mLifeLogDao.insertNoteTagCrossRef(mapperDomain.mapNoteTagDomainToEntity(noteTagCrossRef))

    /*GET METHOD*/
    override suspend fun getAllDate(): List<String> = mLifeLogDao.getAllDates()

    override suspend fun getNotesBasedDate(dateCreated: String): List<NoteDomain> {
        val result = mLifeLogDao.getNotesBasedDate(dateCreated)
        return mapperEntity.mapNotesEntityToDomain(result)
    }

    override suspend fun getNotesBasedFavorite(): List<NoteDomain> {
        val result = mLifeLogDao.getNotesBasedFavorite()
        return mapperEntity.mapNotesEntityToDomain(result)
    }

    override suspend fun getNoteDetails(noteCreatedDate: Long): NoteDomain {
        val result = mLifeLogDao.getNoteDetails(noteCreatedDate)
        return mapperEntity.mapNoteEntityToDomain(result)
    }

    override suspend fun getNoteWithEditLogs(noteCreatedDate: Long): NoteWithEditLogsDomain {
        val result = mLifeLogDao.getNotesWithEditLogs(noteCreatedDate)
        return mapperEntity.mapNoteWithEditLogsEntityToDomain(result)
    }

    override suspend fun getNotesWithTags(noteCreatedDate: Long): NoteWithTagDomain {
        val result = mLifeLogDao.getNotesWithTags(noteCreatedDate)
        return mapperEntity.mapNoteWithTagEntityToDomain(result)
    }

    override suspend fun getTagsWithNote(tagName: String): TagWithNoteDomain {
        val result = mLifeLogDao.getTagsWithNotes(tagName)
        return mapperEntity.mapTagWithNoteEntityToDomain(result)
    }

    /*UPDATE METHOD*/
    override suspend fun updateSelectedNote(note: NoteDomain) {
        mLifeLogDao.updateSelectedNote(mapperDomain.mapNoteDomainToEntity(note))
    }

    /*DELETE METHOD*/
    override suspend fun delSelectedNote(note: NoteDomain) {
        mLifeLogDao.delSelectedNote(mapperDomain.mapNoteDomainToEntity(note))
    }
}