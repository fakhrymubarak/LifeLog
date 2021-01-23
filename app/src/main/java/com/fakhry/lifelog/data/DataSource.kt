package com.fakhry.lifelog.data

import com.fakhry.lifelog.data.local.entities.EditLogEntity
import com.fakhry.lifelog.data.local.entities.NoteEntity
import com.fakhry.lifelog.data.local.relation.NoteWithEditLogs

interface DataSource {
    /*INSERT METHOD*/
     suspend fun insertNote(note: NoteEntity)
     suspend fun insertEdit(editLog: EditLogEntity)

    /*GET METHOD*/
     suspend fun getAllDate(): List<String>
     suspend fun getNotesBasedDate(dateCreated: String): List<NoteEntity>
     suspend fun getNotesBasedFavorite(): List<NoteEntity>
     suspend fun getNoteDetails(idNote: Int): NoteEntity
     suspend fun getNoteWithEditLogs(idNote: Int): List<NoteWithEditLogs>

    /*UPDATE METHOD*/
    suspend fun updateSelectedNote(idNote: Int)

    /*DELETE METHOD*/
    suspend fun delSelectedNote(idNote: Int)
}