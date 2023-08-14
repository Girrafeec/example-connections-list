/* Created by Girrafeec */

package com.girrafeecstud.dependency_coordinator_impl

import android.util.Log
import com.girrafeecstud.dependency_coordinator_api.IUnit
import com.girrafeecstud.example_connections_list.connections_list.di.ConnectionsListUnit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class UnitCreationRequestObserver @Inject constructor(
    private val unitCreationRequestProvider: IUnitCreationRequestProvider,
    private val featureComponentsProvider: FeatureComponentsProvider
) {

    private val observerScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    init {
        Log.i("tag observer", "init")
        observeComponentCreationRequests()
    }

    fun init() {
        Log.i("tag observer", "init x2")
    }

    private fun observeComponentCreationRequests() {
        observerScope.launch {
            unitCreationRequestProvider.getUnitCreationRequests()
                .onEach {
                    handleUnitCreationRequest(unit = it)
                }
                .launchIn(observerScope)
        }
    }

    private fun handleUnitCreationRequest(unit: IUnit) {
        when (unit) {
            ConnectionsListUnit -> {
                featureComponentsProvider.initConnectionsListFeatureComponent()
            }
            else -> throw RuntimeException("Unknown unit creation request. Unit: $unit")
        }
    }

}