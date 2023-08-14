/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.girrafeecstud.core_ui.presentation.BaseViewModel
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection
import com.girrafeecstud.example_connections_list.connections_api.engine.ConnectionsWithDistanceEngineMode
import com.girrafeecstud.example_connections_list.connections_api.engine.IConnectionsWithDistanceEngine
import kotlinx.coroutines.flow.*
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

class ConnectionsWithDistanceViewModel @Inject constructor(
    private val connectionsWithDistanceEngine: IConnectionsWithDistanceEngine
) : ContainerHost<ConnectionsContract.ConnectionsWithDistanceState,
        ConnectionsContract.ConnectionsWithDistanceSideEffect>, ViewModel() {

    override val container: Container<ConnectionsContract.ConnectionsWithDistanceState,
            ConnectionsContract.ConnectionsWithDistanceSideEffect> =
        container(ConnectionsContract.ConnectionsWithDistanceState())

    private var mode = ConnectionsWithDistanceEngineMode.DEFAULT

    private var isLocationPermissionDialogShown: Boolean = false

    private var isEnableGpsDialogShown: Boolean = false

    init {
        getConnectionsWithDistance()
    }

    private fun getConnectionsWithDistance(chosenConnection: Connection? = null) =
        intent {
            when (mode) {
                ConnectionsWithDistanceEngineMode.DEFAULT ->
                    connectionsWithDistanceEngine.startFetchingConnectionsWithDistanceToUser()
                ConnectionsWithDistanceEngineMode.DISTANCE_TO_CHOSEN_CONNECTION ->
                    chosenConnection?.let {
                        connectionsWithDistanceEngine
                            .startFetchingConnectionsWithDistanceToChosenConnection(
                                chosenConnection = chosenConnection
                            )
                    }
            }

            connectionsWithDistanceEngine.getState()
                .onEach { engineState ->

                    reduce {
                        state.copy(isLoading = engineState.isLoading)
                    }

                    engineState.connections?.let {
                        reduce {
                            state.copy(connectionsWithDistance = engineState.connections)
                        }
                    }

                    if (!isLocationPermissionDialogShown && engineState.isLocationPermissionNeeded) {
                        postSideEffect(
                            ConnectionsContract.ConnectionsWithDistanceSideEffect.RequestLocationPermissionsDialog
                        )
                        isLocationPermissionDialogShown = true
                    }

                    if (!isEnableGpsDialogShown && engineState.isGpsNeeded) {
                        postSideEffect(
                            ConnectionsContract.ConnectionsWithDistanceSideEffect.EnableGPSDialog
                        )
                        isEnableGpsDialogShown = true
                    }

                }
                .launchIn(viewModelScope)

        }

    fun getConnectionsWithDistanceToMe() {
        mode = ConnectionsWithDistanceEngineMode.DEFAULT
        getConnectionsWithDistance()
    }

    fun getConnectionsWithDistanceToChosenConnection(chosenConnection: Connection) {
        mode = ConnectionsWithDistanceEngineMode.DISTANCE_TO_CHOSEN_CONNECTION
        getConnectionsWithDistance(chosenConnection = chosenConnection)
    }

    fun pinConnection(connection: Connection) = intent {

        reduce {
            state.copy(isConnectionPinned = true, pinnedConnection = connection)
        }
    }

    fun unpinConnection() = intent {

        reduce {
            state.copy(isConnectionPinned = false, pinnedConnection = null)
        }
    }

    fun respondLocationPermissionRequest(isPermissionGranted: Boolean) =
        intent {
            if (!isPermissionGranted)
                postSideEffect(
                    ConnectionsContract.ConnectionsWithDistanceSideEffect.RequestLocationPermissionRationaleDialog
                )
        }

}