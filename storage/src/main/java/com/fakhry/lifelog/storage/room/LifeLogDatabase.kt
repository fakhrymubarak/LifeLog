package com.fakhry.lifelog.storage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fakhry.lifelog.storage.model.EditLogEntity
import com.fakhry.lifelog.storage.model.NoteEntity
import com.fakhry.lifelog.storage.model.TagEntity
import com.fakhry.lifelog.storage.model.relation.NoteTagCrossRef

@Database(
    entities = [
        NoteEntity::class,
        EditLogEntity::class,
        TagEntity::class,
        NoteTagCrossRef::class
    ],
    version = 1,
    exportSchema = false
)

abstract class LifeLogDatabase : RoomDatabase() {
    abstract fun lifeLogDao(): LifeLogDao

    companion object {
        @Volatile
        private var INSTANCE: LifeLogDatabase? = null

//        private val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("CREATE TABLE note_tag_cross_ref (id_note INTEGER NOT NULL, id_tag INTEGER NOT NULL, PRIMARY KEY(id_note, id_tag))")
//            }
//        }

        fun getInstance(context: Context): LifeLogDatabase {
            if (INSTANCE == null) {
                synchronized(LifeLogDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            LifeLogDatabase::class.java, "LifeLogDatabase.db"
                        )/*.addMigrations(MIGRATION_1_2)*/.build()
                    }
                }
            }
            return INSTANCE as LifeLogDatabase
        }
    }
}