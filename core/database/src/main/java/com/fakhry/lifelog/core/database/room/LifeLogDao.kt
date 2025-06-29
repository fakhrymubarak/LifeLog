package com.fakhry.lifelog.core.database.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.fakhry.lifelog.core.database.model.EditLogEntity
import com.fakhry.lifelog.core.database.model.NoteEntity
import com.fakhry.lifelog.core.database.model.TagEntity
import com.fakhry.lifelog.core.database.model.relation.NoteTagCrossRef
import com.fakhry.lifelog.core.database.model.relation.NoteWithEditLogsRelation
import com.fakhry.lifelog.core.database.model.relation.NoteWithTagRelation
import com.fakhry.lifelog.core.database.model.relation.TagWithNoteRelation

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
    @Query("SELECT DISTINCT created_date FROM note_entity ORDER BY note_created_date DESC")
    suspend fun getAllDates(): List<String>

    @Query("SELECT * FROM note_entity WHERE created_date=:dateCreated ORDER BY note_created_date DESC")
    suspend fun getNotesBasedDate(dateCreated: String): List<NoteEntity>

    @Query("SELECT * FROM note_entity WHERE is_fav_note=1 ORDER BY note_created_date DESC")
    suspend fun getNotesBasedFavorite(): List<NoteEntity>

    @Query("SELECT * FROM note_entity WHERE note_created_date=:noteCreatedDate")
    suspend fun getNoteDetails(noteCreatedDate: Long): NoteEntity

    @Transaction
    @Query("SELECT * FROM note_entity WHERE note_created_date=:noteCreatedDate")
    suspend fun getNotesWithEditLogs(noteCreatedDate: Long): NoteWithEditLogsRelation

    @Transaction
    @Query("SELECT * FROM note_entity WHERE note_created_date=:noteCreatedDate")
    suspend fun getNotesWithTags(noteCreatedDate: Long): NoteWithTagRelation

    @Transaction
    @Query("SELECT * FROM tag_entity WHERE tag_name=:tagName")
    suspend fun getTagsWithNotes(tagName: String): TagWithNoteRelation

    /*UPDATE METHOD*/
    @Update
    suspend fun updateSelectedNote(note: NoteEntity)

    /*DELETE METHOD*/
    //    @Query("DELETE FROM note_entity WHERE note_created_date=:idNote")
    @Delete
    suspend fun delSelectedNote(note: NoteEntity)

    @Query("DELETE FROM note_tag_cross_ref WHERE tag_name=:tagName")
    suspend fun delTagFromNote(tagName :String)

    @Query("DELETE FROM note_tag_cross_ref WHERE tag_name=:tagName")
    suspend fun deleteTag(tagName :String)
}