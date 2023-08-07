/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_impl.data

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.example_connections_list.connections_api.data.IConnectionsDataSource
import com.girrafeecstud.example_connections_list.connections_api.domain.repository.IConnectionsRepository
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConnectionsRepository @Inject constructor(
    private val dataSource: IConnectionsDataSource
) : IConnectionsRepository {

    override fun getConnections(): Flow<BusinessResult<List<Connection>>> =
        dataSource.getConnections()
}