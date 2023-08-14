/* Created by Girrafeec */

package com.girrafeecstud.dependency_coordinator_impl

import com.girrafeecstud.dependency_coordinator_api.IUnit
import com.girrafeecstud.dependency_coordinator_api.IUnitCreationRequestHandler
import javax.inject.Inject

class UnitCreationRequestHandler @Inject constructor(
    private val unitCreationRequestProvider: UnitCreationRequestProvider
) : IUnitCreationRequestHandler {
//TODO what to do with component destruction? Where and when to observe it
    override fun requestUnitCreation(unit: IUnit) =
        unitCreationRequestProvider.requestUnitCreation(unit = unit)
}