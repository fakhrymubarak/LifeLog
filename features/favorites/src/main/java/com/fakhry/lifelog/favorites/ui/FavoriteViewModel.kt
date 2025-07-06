package com.fakhry.lifelog.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.core.database.model.DateNoteEntity
import com.fakhry.lifelog.core.database.model.NoteEntity
import com.fakhry.lifelog.core.database.room.LocalDataSource
import com.fakhry.lifelog.utils.coroutines.DispatcherProvider
import com.fakhry.lifelog.utils.state.UiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val dataSource: LocalDataSource,
    private val dispatcher: DispatcherProvider,
) : ViewModel() {

    private val _favoritesState =
        MutableStateFlow<UiResult<List<DateNoteEntity>>>(UiResult.Uninitialized)
    val favoritesState = _favoritesState.asStateFlow()

    init {
        getFavoriteNote()
    }

    private fun getFavoriteNote() = viewModelScope.launch(dispatcher.io) {
        _favoritesState.update { UiResult.Loading }
        val result = dataSource.getNotesBasedFavorite()
        _favoritesState.update {
            if (result.isEmpty()) {
                UiResult.Empty
            } else {
                UiResult.Success(groupNotesByDate(result))
            }
        }
    }

    private fun groupNotesByDate(notes: List<NoteEntity>): List<DateNoteEntity> {
        return notes
            .groupBy { it.createdDate }
            .map { (date, notesOnDate) ->
                DateNoteEntity(date, notesOnDate)
            }
    }
}