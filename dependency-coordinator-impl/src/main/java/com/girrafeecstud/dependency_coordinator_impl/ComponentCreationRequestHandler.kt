/* Created by Girrafeec */

package com.girrafeecstud.dependency_coordinator_impl

import com.girrafeecstud.dependency_coordinator_api.IComponent
import com.girrafeecstud.dependency_coordinator_api.IComponentCreationRequestHandler
import javax.inject.Inject

class ComponentCreationRequestHandler @Inject constructor(
    private val componentCreationRequestProvider: ComponentCreationRequestProvider
) : IComponentCreationRequestHandler {
//TODO what to do with component destruction? Where and when to observe it
    override fun requestComponentCreation(component: IComponent) =
        componentCreationRequestProvider.requestComponentCreation(component = component)
}