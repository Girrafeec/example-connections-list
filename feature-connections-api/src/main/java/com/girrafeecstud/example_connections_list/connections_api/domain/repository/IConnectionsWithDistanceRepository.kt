/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_api.domain.repository

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.ConnectionWithDistance
import com.girrafeecstud.location_api.domain.Location
import kotlinx.coroutines.flow.Flow

interface IConnectionsWithDistanceRepository {

    fun getConnectionsWithDistanceToUser(): Flow<BusinessResult<List<ConnectionWithDistance>>>

    fun getConnectionsWithDistanceToChosenConnection(
        chosenConnection: Connection
    ): Flow<BusinessResult<List<ConnectionWithDistance>>>

}