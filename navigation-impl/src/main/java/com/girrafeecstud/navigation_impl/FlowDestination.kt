/* Created by Girrafeec */

package com.girrafeecstud.navigation_impl

import com.girrafeecstud.navigation_api.INavigationDestination

sealed class FlowDestination(
    override val destinationId: Int,
    override val args: Map<String, Any> = emptyMap()
) : INavigationDestination {

    object ConnectionsListFlow : FlowDestination(
        destinationId = R.id.connections_list_flow_fragment,
    )

}