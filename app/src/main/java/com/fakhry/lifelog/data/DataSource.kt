package com.fakhry.lifelog.data

interface DataSource {
    /*Offline Data Source*/
    fun addNewNote()

    fun getNoteAll()
    fun getNoteBasedDate()
    fun getNoteBasedFavorite()

    fun updateNoteFavorite()
    fun updateNote()

    fun deleteNote()
}