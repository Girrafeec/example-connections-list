/* Created by Girrafeec */

package com.girrafeecstud.navigation_impl.navigator

import com.girrafeecstud.navigation_api.IConnectionsListExternalScreensNavigator
import javax.inject.Inject

class ConnectionsListExternalScreensNavigator @Inject constructor(
    private val flowNavigator: FlowNavigator
) : IConnectionsListExternalScreensNavigator {

    // This method uses FlowNavigator for opening screen located in another module
    // in relation to the current module.
    // Also some data can be sent as input parameter/-s.
    override fun openSomeScreen() {}
}