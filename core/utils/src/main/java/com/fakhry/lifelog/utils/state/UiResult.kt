package com.fakhry.lifelog.utils.state

sealed class UiResult<out T> {
    data object Uninitialized : UiResult<Nothing>()
    data object Loading : UiResult<Nothing>()
    data object Empty : UiResult<Nothing>()
    data class Success<out T>(val data: T) : UiResult<T>()
    data class Error<out T, out E>(val message: T? = null, val dataError: E) : UiResult<E>()
}
