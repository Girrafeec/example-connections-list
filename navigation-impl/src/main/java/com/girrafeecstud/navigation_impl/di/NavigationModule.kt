package com.girrafeecstud.navigation_impl.di

import com.girrafeecstud.navigation_api.IConnectionsListExternalScreensNavigator
import com.girrafeecstud.navigation_api.IExternalScreensNavigator
import com.girrafeecstud.navigation_api.INavigator
import com.girrafeecstud.navigation_impl.FlowDestination
import com.girrafeecstud.navigation_impl.navigator.ConnectionsListExternalScreensNavigator
import com.girrafeecstud.navigation_impl.navigator.FlowNavigator
import dagger.Binds
import dagger.Module
import dagger.Provides
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
