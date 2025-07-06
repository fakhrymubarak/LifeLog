package com.fakhry.lifelog.utils.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

data class DispatcherProvider(
    val main: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
    val io: CoroutineDispatcher
) {
    constructor() : this(Main, Default, IO)

    constructor(testDispatcher: CoroutineDispatcher) : this (testDispatcher, testDispatcher, testDispatcher)
}
