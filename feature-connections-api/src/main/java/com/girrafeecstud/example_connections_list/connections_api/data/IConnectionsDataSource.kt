/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_api.data

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection
import kotlinx.coroutines.flow.Flow

interface IConnectionsDataSource {

    fun getConnections(): Flow<BusinessResult<List<Connection>>>

}