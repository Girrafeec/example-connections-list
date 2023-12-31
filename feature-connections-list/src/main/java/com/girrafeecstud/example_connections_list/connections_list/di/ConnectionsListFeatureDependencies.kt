package com.girrafeecstud.example_connections_list.connections_list.di

import com.girrafeecstud.core_base.di.IComponentDependencies
import com.girrafeecstud.core_components_api.DispatcherProvider
import com.girrafeecstud.example_connections_list.connections_api.engine.IConnectionsWithDistanceEngine
import com.girrafeecstud.navigation_api.IConnectionsListExternalScreensNavigator
import com.girrafeecstud.navigation_api.IExternalScreensNavigator

interface ConnectionsListFeatureDependencies : IComponentDependencies {

    fun getDispatcherProvider(): DispatcherProvider

    fun getConnectionsWithDistanceEngine(): IConnectionsWithDistanceEngine

    fun getConnectionsListExternalScreensNavigator(): IConnectionsListExternalScreensNavigator

}
