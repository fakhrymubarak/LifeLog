package com.fakhry.lifelog.data.local.room

import androidx.room.*
import com.fakhry.lifelog.data.local.entities.EditLogEntity
import com.fakhry.lifelog.data.local.entities.NoteEntity
import com.fakhry.lifelog.data.local.entities.TagEntity
import com.fakhry.lifelog.data.local.relation.NoteTagCrossRef
import com.fakhry.lifelog.data.local.relation.NoteWithEditLogsRelation
import com.fakhry.lifelog.data.local.relation.NoteWithTagRelation
import com.fakhry.lifelog.data.local.relation.TagWithNoteRelation

@Dao
interface LifeLogDao {

    /*INSERT METHOD*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEdit(editLog: EditLogEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(tag: TagEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNoteTagCrossRef(noteTagCrossRef: NoteTagCrossRef)

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
    suspend fun getNoteWithEditLogs(idNote: Int): List<NoteWithEditLogsRelation>
    @Transaction

    @Query("SELECT * FROM note_entity WHERE id_note=:idNote")
    suspend fun getNoteWithTags(idNote: Int): List<NoteWithTagRelation>

    @Transaction
    @Query("SELECT * FROM note_entity WHERE id_note=:idTag")
    suspend fun getTagsWithNote(idTag: Int): List<TagWithNoteRelation>

    /*UPDATE METHOD*/
    @Update
    suspend fun updateSelectedNote(idNote: Int)

    /*DELETE METHOD*/

//    @Query("DELETE FROM note_entity WHERE id_note=:idNote")
    @Delete
    suspend fun delSelectedNote(idNote: Int)
}