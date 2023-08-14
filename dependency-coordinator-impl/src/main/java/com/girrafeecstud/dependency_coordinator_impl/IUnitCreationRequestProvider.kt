/* Created by Girrafeec */

package com.girrafeecstud.dependency_coordinator_impl

import com.girrafeecstud.dependency_coordinator_api.IUnit
import com.girrafeecstud.dependency_coordinator_api.IUnitCreationRequestHandler
import kotlinx.coroutines.flow.Flow

interface IUnitCreationRequestProvider : IUnitCreationRequestHandler {

    fun getUnitCreationRequests(): Flow<IUnit>

}
