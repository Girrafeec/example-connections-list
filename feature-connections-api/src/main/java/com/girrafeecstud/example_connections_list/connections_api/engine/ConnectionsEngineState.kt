package com.girrafeecstud.example_connections_list.connections_api.engine

import com.girrafeecstud.core_base.domain.base.CommonError
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection

data class ConnectionsEngineState(
    val isLoading: Boolean = false,
    val connections: List<Connection>? = null,
    val error: CommonError? = null
)
