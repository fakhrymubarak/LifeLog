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
    @Query("SELECT DISTINCT created_date FROM note_entity")
    suspend fun getAllDates(): List<String>

    @Query("SELECT * FROM note_entity WHERE created_date=:dateCreated")
    suspend fun getNotesBasedDate(dateCreated: String): List<NoteEntity>

    @Query("SELECT * FROM note_entity WHERE is_fav_note=1")
    suspend fun getNotesBasedFavorite(): List<NoteEntity>

    @Query("SELECT * FROM note_entity WHERE note_created_date=:noteCreatedDate")
    suspend fun getNoteDetails(noteCreatedDate: Long): NoteEntity

    @Transaction
    @Query("SELECT * FROM note_entity WHERE note_created_date=:noteCreatedDate")
    suspend fun getNotesWithEditLogs(noteCreatedDate: Long): NoteWithEditLogsRelation

    @Transaction
    @Query("SELECT * FROM note_entity WHERE note_created_date=:noteCreatedDate")
    suspend fun getNotesWithTags(noteCreatedDate: Long): List<NoteWithTagRelation>

    @Transaction
    @Query("SELECT * FROM tag_entity WHERE tag_name=:tagName")
    suspend fun getTagsWithNotes(tagName: String): List<TagWithNoteRelation>

    /*UPDATE METHOD*/
    @Update
    suspend fun updateSelectedNote(note: NoteEntity)

    /*DELETE METHOD*/
    //    @Query("DELETE FROM note_entity WHERE id_note=:idNote")
    @Delete
    suspend fun delSelectedNote(note: NoteEntity)
}