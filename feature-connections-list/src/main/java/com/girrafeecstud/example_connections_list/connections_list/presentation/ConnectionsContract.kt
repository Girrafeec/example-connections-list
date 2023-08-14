/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_list.presentation

import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.ConnectionWithDistance

interface ConnectionsContract {

    data class ConnectionsWithDistanceState(
        val connectionsWithDistance: List<ConnectionWithDistance>? = null,
        val isLoading: Boolean = false,
        val isConnectionPinned: Boolean = false,
        val pinnedConnection: Connection? = null
    )

    sealed class ConnectionsWithDistanceSideEffect {
        object EnableGPSDialog : ConnectionsWithDistanceSideEffect()
        object RequestLocationPermissionsDialog : ConnectionsWithDistanceSideEffect()
        object RequestLocationPermissionRationaleDialog : ConnectionsWithDistanceSideEffect()
    }

}