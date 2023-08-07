/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_impl.domain

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.example_connections_list.connections_api.domain.repository.IConnectionsWithDistanceRepository
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.ConnectionWithDistance
import com.girrafeecstud.example_connections_list.connections_api.domain.usecase.IGetConnectionsWithDistanceToChosenConnectionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetConnectionsWithDistanceToChosenConnectionUseCase @Inject constructor(
    private val repository: IConnectionsWithDistanceRepository
) : IGetConnectionsWithDistanceToChosenConnectionUseCase {

    override fun invoke(chosenConnection: Connection): Flow<BusinessResult<List<ConnectionWithDistance>>> =
        repository.getConnectionsWithDistanceToChosenConnection(chosenConnection = chosenConnection)
            .flowOn(Dispatchers.IO)

}