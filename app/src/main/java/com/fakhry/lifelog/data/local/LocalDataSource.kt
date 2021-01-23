package com.fakhry.lifelog.data.local

import com.fakhry.lifelog.data.local.room.LifeLogDao

class LocalDataSource private constructor(private val mLifeLogDao : LifeLogDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(lifeLogDao: LifeLogDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(lifeLogDao)
            }
            return INSTANCE as LocalDataSource
        }
    }
}