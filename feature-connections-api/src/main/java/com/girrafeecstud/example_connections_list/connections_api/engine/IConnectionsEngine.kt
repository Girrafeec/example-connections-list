/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_api.engine

import kotlinx.coroutines.flow.Flow

interface IConnectionsEngine {

    fun startConnectionsEngine()

    fun getState(): Flow<ConnectionsEngineState>

}