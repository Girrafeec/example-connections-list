/* Created by Girrafeec */

package com.girrafeecstud.core_components_api

import kotlinx.coroutines.CoroutineDispatcher

class TestDispatchers(
    private val testDispatcher: CoroutineDispatcher
) : DispatcherProvider {
    override val default: CoroutineDispatcher = testDispatcher
    override val main: CoroutineDispatcher = testDispatcher
    override val io: CoroutineDispatcher = testDispatcher
}