package com.fakhry.lifelog.utils.state

inline fun <reified T> UiResult<T>.isLoading() = this is UiResult.Loading

inline fun <reified T> UiResult<T>.isEmpty() = this is UiResult.Empty