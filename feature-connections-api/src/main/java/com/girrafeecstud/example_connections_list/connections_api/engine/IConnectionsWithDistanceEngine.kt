/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_api.engine

import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection
import kotlinx.coroutines.flow.Flow

interface IConnectionsWithDistanceEngine {

    fun startFetchingConnectionsWithDistanceToUser()

    fun startFetchingConnectionsWithDistanceToChosenConnection(chosenConnection: Connection)

    fun getState(): Flow<ConnectionsWithDistanceEngineState>

}