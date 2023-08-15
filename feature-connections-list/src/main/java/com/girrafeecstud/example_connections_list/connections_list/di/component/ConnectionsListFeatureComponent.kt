/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_list.di.component

import com.girrafeecstud.core_components_api.di.CoreComponentsApi
import com.girrafeecstud.example_connections_list.connections_api.di.ConnectionsFeatureApi
import com.girrafeecstud.example_connections_list.connections_list.di.ConnectionsListFeatureApi
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
interface ConnectionsListFeatureComponent : ConnectionsListFeatureApi {

    fun inject(fragment: ConnectionsListFlowFragment)

    fun connectionsListComponent(): ConnectionsListComponent.Builder

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: ConnectionsListFeatureDependencies): Builder

        fun build(): ConnectionsListFeatureComponent

    }

    companion object {

        fun initAndGet(dependencies: ConnectionsListFeatureDependencies): ConnectionsListFeatureComponent {
            return DaggerConnectionsListFeatureComponent
                .builder()
                .dependencies(dependencies = dependencies)
                .build()
        }

    }

    @ConnectionsListFeatureScope
    @Component(
        dependencies = [
            CoreComponentsApi::class,
            ConnectionsFeatureApi::class,
            NavigationApi::class
        ]
    )
    interface ConnectionsListFeatureDependenciesComponent : ConnectionsListFeatureDependencies

}