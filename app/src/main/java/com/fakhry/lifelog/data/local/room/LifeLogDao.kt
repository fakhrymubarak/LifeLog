package com.fakhry.lifelog.data.local.room

import androidx.room.*
import com.fakhry.lifelog.data.local.entities.EditLogEntity
import com.fakhry.lifelog.data.local.entities.NoteEntity
import com.fakhry.lifelog.data.local.relation.NoteWithEditLogs

@Dao
interface LifeLogDao {

    /*INSERT METHOD*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEdit(editLog: EditLogEntity)

    /*GET METHOD*/
    @Query("SELECT DISTINCT date_created FROM note_entity")
    suspend fun getAllDate(): List<String>

    @Query("SELECT * FROM note_entity WHERE date_created=:dateCreated")
    suspend fun getNotesBasedDate(dateCreated: String): List<NoteEntity>

    @Query("SELECT * FROM note_entity WHERE is_fav_note=1")
    suspend fun getNotesBasedFavorite(): List<NoteEntity>

    @Query("SELECT * FROM note_entity WHERE id_note=id_note")
    suspend fun getNoteDetails(idNote: Int): NoteEntity

    @Transaction
    @Query("SELECT * FROM note_entity WHERE id_note=:idNote")
    suspend fun getNoteWithEditLogs(idNote: Int): List<NoteWithEditLogs>

    /*UPDATE METHOD*/
    @Update
    suspend fun updateSelectedNote(idNote: Int)

    /*DELETE METHOD*/

//    @Query("DELETE FROM note_entity WHERE id_note=:idNote")
    @Delete
    suspend fun delSelectedNote(idNote: Int)
}