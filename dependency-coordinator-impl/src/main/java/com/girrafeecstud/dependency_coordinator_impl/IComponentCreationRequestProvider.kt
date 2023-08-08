/* Created by Girrafeec */

package com.girrafeecstud.dependency_coordinator_impl

import com.girrafeecstud.dependency_coordinator_api.IComponent
import com.girrafeecstud.dependency_coordinator_api.IComponentCreationRequestHandler
import kotlinx.coroutines.flow.Flow

interface IComponentCreationRequestProvider : IComponentCreationRequestHandler {

    fun getComponentCreationRequests(): Flow<IComponent>

}
