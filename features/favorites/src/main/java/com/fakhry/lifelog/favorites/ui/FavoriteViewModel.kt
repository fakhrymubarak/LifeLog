package com.fakhry.lifelog.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fakhry.lifelog.domain.model.DateNoteDomain
import com.fakhry.lifelog.domain.model.NoteDomain
import com.fakhry.lifelog.domain.repository.NoteLocalRepository
import com.fakhry.lifelog.utils.coroutines.DispatcherProvider
import com.fakhry.lifelog.utils.state.UiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repos: NoteLocalRepository,
    private val dispatcher: DispatcherProvider,
) : ViewModel() {

    private val _favoritesState =
        MutableStateFlow<UiResult<List<DateNoteDomain>>>(UiResult.Uninitialized)
    val favoritesState = _favoritesState.asStateFlow()

    fun getFavoriteNote() = viewModelScope.launch(dispatcher.io) {
        _favoritesState.update { UiResult.Loading }
        val result = repos.getNotesBasedFavorite()
        _favoritesState.update {
            if (result.isEmpty()) {
                UiResult.Empty
            } else {
                UiResult.Success(groupNotesByDate(result))
            }
        }
    }

    // MOVE TO USE CASES
    private fun groupNotesByDate(notes: List<NoteDomain>): List<DateNoteDomain> {
        return notes
            .groupBy { it.createdDate }
            .map { (date, notesOnDate) ->
                DateNoteDomain(date, notesOnDate)
            }
    }
}