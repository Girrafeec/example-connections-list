/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_api.domain.usecase

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.ConnectionWithDistance
import kotlinx.coroutines.flow.Flow

interface IGetConnectionsWithDistanceToChosenConnectionUseCase {
    operator fun invoke(chosenConnection: Connection): Flow<BusinessResult<List<ConnectionWithDistance>>>
}