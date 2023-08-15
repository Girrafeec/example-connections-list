/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_impl.data

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.core_components_api.DispatcherProvider
import com.girrafeecstud.example_connections_list.connections_api.data.IConnectionsDataSource
import com.girrafeecstud.example_connections_list.connections_api.domain.repository.IConnectionsRepository
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ConnectionsRepository @Inject constructor(
    private val dataSource: IConnectionsDataSource,
    private val dispatchers: DispatcherProvider
) : IConnectionsRepository {

    override fun getConnections(): Flow<BusinessResult<List<Connection>>> =
        dataSource.getConnections().flowOn(dispatchers.io)
}