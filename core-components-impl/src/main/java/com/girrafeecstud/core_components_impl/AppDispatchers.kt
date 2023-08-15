/* Created by Girrafeec */

package com.girrafeecstud.core_components_impl

import com.girrafeecstud.core_components_api.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AppDispatchers @Inject constructor() : DispatcherProvider {
    override val default: CoroutineDispatcher = Dispatchers.Default
    override val main: CoroutineDispatcher = Dispatchers.Main
    override val io: CoroutineDispatcher = Dispatchers.IO
}