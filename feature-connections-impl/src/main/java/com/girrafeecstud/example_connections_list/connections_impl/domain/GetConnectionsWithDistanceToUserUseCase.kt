/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_impl.domain

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.core_components_api.DispatcherProvider
import com.girrafeecstud.example_connections_list.connections_api.domain.repository.IConnectionsWithDistanceRepository
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.ConnectionWithDistance
import com.girrafeecstud.example_connections_list.connections_api.domain.usecase.IGetConnectionsWithDistanceToUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetConnectionsWithDistanceToUserUseCase @Inject constructor(
    private val repository: IConnectionsWithDistanceRepository,
    private val dispatchers: DispatcherProvider
) : IGetConnectionsWithDistanceToUserUseCase {

    override fun invoke(): Flow<BusinessResult<List<ConnectionWithDistance>>> =
        repository.getConnectionsWithDistanceToUser().flowOn(dispatchers.io)

}