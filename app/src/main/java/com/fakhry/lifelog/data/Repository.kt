package com.fakhry.lifelog.data

import com.fakhry.lifelog.data.local.LocalDataSource

class Repository(private val mLocalDataSource: LocalDataSource) : DataSource {
    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            localDataSource: LocalDataSource,
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(localDataSource)
            }
    }

    override fun addNewNote() {
        TODO("Not yet implemented")
    }

    override fun getNoteAll() {
        TODO("Not yet implemented")
    }

    override fun getNoteBasedDate() {
        TODO("Not yet implemented")
    }

    override fun getNoteBasedFavorite() {
        TODO("Not yet implemented")
    }

    override fun updateNoteFavorite() {
        TODO("Not yet implemented")
    }

    override fun updateNote() {
        TODO("Not yet implemented")
    }

    override fun deleteNote() {
        TODO("Not yet implemented")
    }
}