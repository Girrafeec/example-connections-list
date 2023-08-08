/* Created by Girrafeec */

package com.girrafeecstud.dependency_coordinator_impl

import com.girrafeecstud.dependency_coordinator_api.IComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ComponentCreationRequestProvider @Inject constructor(

) : IComponentCreationRequestProvider {

    private val componentCreationRequestProviderScope =
        CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var _componentCreationRequests =
        MutableSharedFlow<IComponent>(replay = 10)

    override fun requestComponentCreation(component: IComponent) {
        componentCreationRequestProviderScope.launch {
            _componentCreationRequests.emit(component)
        }
    }

    override fun getComponentCreationRequests(): Flow<IComponent> =
        _componentCreationRequests.asSharedFlow()
}