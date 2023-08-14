/* Created by Girrafeec */

package com.girrafeecstud.dependency_coordinator_impl

import com.girrafeecstud.dependency_coordinator_api.IUnit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class UnitCreationRequestProvider @Inject constructor(

) : IUnitCreationRequestProvider {

    private val componentCreationRequestProviderScope =
        CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var _componentCreationRequests =
        MutableSharedFlow<IUnit>(replay = 10)

    override fun requestUnitCreation(unit: IUnit) {
        componentCreationRequestProviderScope.launch {
            _componentCreationRequests.emit(unit)
        }
    }

    override fun getUnitCreationRequests(): Flow<IUnit> =
        _componentCreationRequests.asSharedFlow()
}