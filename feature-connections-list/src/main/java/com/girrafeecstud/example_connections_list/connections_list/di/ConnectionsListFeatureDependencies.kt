package com.girrafeecstud.example_connections_list.connections_list.di

import com.girrafeecstud.example_connections_list.connections_api.engine.IConnectionsWithDistanceEngine
import com.girrafeecstud.navigation_api.IConnectionsListExternalScreensNavigator
import com.girrafeecstud.navigation_api.IExternalScreensNavigator

interface ConnectionsListFeatureDependencies {

    fun getConnectionsWithDistanceEngine(): IConnectionsWithDistanceEngine

    fun getConnectionsListExternalScreensNavigator(): IConnectionsListExternalScreensNavigator

}
