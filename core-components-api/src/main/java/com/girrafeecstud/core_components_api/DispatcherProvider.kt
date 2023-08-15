/* Created by Girrafeec */

package com.girrafeecstud.core_components_api

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}