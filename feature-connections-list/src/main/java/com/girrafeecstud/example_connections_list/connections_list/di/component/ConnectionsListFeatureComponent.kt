/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_list.di.component

import com.girrafeecstud.example_connections_list.connections_api.di.ConnectionsFeatureApi
import com.girrafeecstud.example_connections_list.connections_list.di.ConnectionsListFeatureDependencies
import com.girrafeecstud.example_connections_list.connections_list.di.module.ConnectionsListFeatureModule
import com.girrafeecstud.example_connections_list.connections_list.di.annotation.ConnectionsListFeatureScope
import com.girrafeecstud.example_connections_list.connections_list.ui.ConnectionsListFlowFragment
import com.girrafeecstud.navigation_api.di.NavigationApi
import dagger.Component

@ConnectionsListFeatureScope
@Component(
    modules = [ConnectionsListFeatureModule::class],
    dependencies = [ConnectionsListFeatureDependencies::class]
)
interface ConnectionsListFeatureComponent {

    fun inject(fragment: ConnectionsListFlowFragment)

    fun connectionsListComponent(): ConnectionsListComponent.Builder

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: ConnectionsListFeatureDependencies): Builder

        fun build(): ConnectionsListFeatureComponent

    }

    companion object {

        private var _connectionsListFeatureComponent: ConnectionsListFeatureComponent? = null

        @Synchronized
        fun initAndGet(dependencies: ConnectionsListFeatureDependencies): ConnectionsListFeatureComponent {
            if (_connectionsListFeatureComponent == null)
                _connectionsListFeatureComponent = DaggerConnectionsListFeatureComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
            return _connectionsListFeatureComponent!!
        }

        fun get(): ConnectionsListFeatureComponent {
            if (_connectionsListFeatureComponent == null)
                throw RuntimeException("ConnectionsListFeatureComponent is not initialized. You must call 'initAndGet(dependencies: ConnectionsListFeatureDependencies)' method")
            return _connectionsListFeatureComponent!!
        }

        fun reset() {
            _connectionsListFeatureComponent = null
        }

    }

    @ConnectionsListFeatureScope
    @Component(
        dependencies = [
            ConnectionsFeatureApi::class,
            NavigationApi::class
        ]
    )
    interface ConnectionsListFeatureDependenciesComponent : ConnectionsListFeatureDependencies

}