/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_impl.engine

import android.content.Context
import android.content.Intent
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection
import com.girrafeecstud.example_connections_list.connections_api.engine.ConnectionsWithDistanceEngineMode
import com.girrafeecstud.example_connections_list.connections_api.engine.ConnectionsWithDistanceEngineState
import com.girrafeecstud.example_connections_list.connections_api.engine.IConnectionsWithDistanceEngine
import com.girrafeecstud.example_connections_list.connections_impl.service.ConnectionsWithDistanceService
import com.girrafeecstud.example_connections_list.connections_impl.util.ConnectionsFeatureUtils
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConnectionsWithDistanceEngine @Inject constructor(
    private val context: Context
) : IConnectionsWithDistanceEngine {

    override fun startFetchingConnectionsWithDistanceToUser() {
        sendCommandToService(mode = ConnectionsWithDistanceEngineMode.DEFAULT)
    }

    override fun startFetchingConnectionsWithDistanceToChosenConnection(chosenConnection: Connection) {
        sendCommandToService(
            mode = ConnectionsWithDistanceEngineMode.DISTANCE_TO_CHOSEN_CONNECTION,
            chosenConnection = chosenConnection
        )
    }

    override fun getState(): Flow<ConnectionsWithDistanceEngineState> =
        ConnectionsWithDistanceService.state

    private fun sendCommandToService(
        mode: ConnectionsWithDistanceEngineMode,
        chosenConnection: Connection? = null
    ) {
        val intent = Intent(context, ConnectionsWithDistanceService::class.java).apply {
            val serviceMode = when (mode) {
                ConnectionsWithDistanceEngineMode.DEFAULT ->
                    ConnectionsFeatureUtils.CONNECTIONS_WITH_DISTANCE_TO_USER_MODE
                ConnectionsWithDistanceEngineMode.DISTANCE_TO_CHOSEN_CONNECTION ->
                    ConnectionsFeatureUtils.CONNECTIONS_WITH_DISTANCE_TO_CHOSEN_CONNECTION_MODE
            }
            this.putExtra(ConnectionsFeatureUtils.CONNECTIONS_WITH_DISTANCE_SERVICE_MODE, serviceMode)
            this.putExtra(ConnectionsFeatureUtils.CHOSEN_CONNECTION, chosenConnection)
        }
        context.startService(intent)
    }
}