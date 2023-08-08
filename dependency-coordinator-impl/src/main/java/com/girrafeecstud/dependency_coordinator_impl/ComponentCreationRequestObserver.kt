/* Created by Girrafeec */

package com.girrafeecstud.dependency_coordinator_impl

import com.girrafeecstud.dependency_coordinator_api.IComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class ComponentCreationRequestObserver @Inject constructor(
    private val componentCreationRequestProvider: IComponentCreationRequestProvider,
    private val featureComponentsProvider: FeatureComponentsProvider
) {

    private val observerScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        observeComponentCreationRequests()
    }

    private fun observeComponentCreationRequests() {
        observerScope.launch {
            componentCreationRequestProvider.getComponentCreationRequests()
                .onEach {
                    handleComponentCreationRequest(component = it)
                }
                .launchIn(observerScope)
        }
    }

    // TODO finish method with components
    private fun handleComponentCreationRequest(component: IComponent) {

    }

}