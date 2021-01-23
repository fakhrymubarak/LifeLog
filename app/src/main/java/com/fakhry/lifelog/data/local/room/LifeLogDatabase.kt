package com.fakhry.lifelog.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fakhry.lifelog.data.local.entities.EditLogEntity
import com.fakhry.lifelog.data.local.entities.NoteEntity
import com.fakhry.lifelog.data.local.entities.TagEntity
import com.fakhry.lifelog.data.local.relation.NoteTagCrossRef

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

        fun getInstance(context: Context): LifeLogDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            LifeLogDatabase::class.java, "LifeLogDatabase.db"
                        ).build().also {
                            INSTANCE = it
                        }
                    }
                }
            }
            return INSTANCE as LifeLogDatabase
        }
    }
}