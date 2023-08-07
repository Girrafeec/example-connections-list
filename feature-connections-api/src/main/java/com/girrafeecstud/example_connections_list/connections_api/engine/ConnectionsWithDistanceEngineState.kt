package com.girrafeecstud.example_connections_list.connections_api.engine

import com.girrafeecstud.core_base.domain.base.CommonError
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.ConnectionWithDistance

data class ConnectionsWithDistanceEngineState(
    val isLoading: Boolean = false,
    val connections: List<ConnectionWithDistance>? = null,
    val error: CommonError? = null
)
