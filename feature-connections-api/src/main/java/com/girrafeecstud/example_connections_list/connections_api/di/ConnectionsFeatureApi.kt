/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_api.di

import com.girrafeecstud.example_connections_list.connections_api.domain.repository.IConnectionsRepository
import com.girrafeecstud.example_connections_list.connections_api.data.IConnectionsDataSource
import com.girrafeecstud.example_connections_list.connections_api.domain.repository.IConnectionsWithDistanceRepository
import com.girrafeecstud.example_connections_list.connections_api.engine.IConnectionsEngine
import com.girrafeecstud.example_connections_list.connections_api.engine.IConnectionsWithDistanceEngine

interface ConnectionsFeatureApi {

    fun getConnectionsDataSource(): IConnectionsDataSource

    fun getConnectionRepository(): IConnectionsRepository

    fun getConnectionsWithDistanceRepository(): IConnectionsWithDistanceRepository

    fun getConnectionsWithDistanceEngine(): IConnectionsWithDistanceEngine

}