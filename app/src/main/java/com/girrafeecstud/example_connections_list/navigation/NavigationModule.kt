package com.girrafeecstud.example_connections_list.navigation

import com.girrafeecstud.navigation_api.IConnectionsListExternalScreensNavigator
import com.girrafeecstud.navigation_api.INavigator
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NavigationModule {

    @Binds
    @Singleton
    fun bindFlowNavigator(impl: FlowNavigator): INavigator<FlowDestination>

    @Binds
    @Singleton
    fun bindConnectionsListExternalScreensNavigator(
        impl: ConnectionsListExternalScreensNavigator
    ): IConnectionsListExternalScreensNavigator

}
