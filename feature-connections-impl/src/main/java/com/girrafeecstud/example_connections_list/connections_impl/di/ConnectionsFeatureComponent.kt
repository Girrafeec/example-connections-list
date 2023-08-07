/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_impl.di

import com.girrafeecstud.core_components_api.di.CoreComponentsApi
import com.girrafeecstud.example_connections_list.connections_api.di.ConnectionsFeatureApi
import com.girrafeecstud.example_connections_list.connections_impl.service.ConnectionsWithDistanceService
import com.girrafeecstud.location_api.di.LocationFeatureApi
import com.girrafeecstud.location_tracker_api.di.LocationTrackerFeatureApi
import dagger.Component

@ConnectionsFeatureScope
@Component(
    modules = [ConnectionsFeatureModule::class],
    dependencies = [ConnectionsFeatureDependencies::class]
)
interface ConnectionsFeatureComponent : ConnectionsFeatureApi {

    fun inject(service: ConnectionsWithDistanceService)

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: ConnectionsFeatureDependencies): Builder

        fun build(): ConnectionsFeatureComponent

    }

    companion object {

        private var _connectionsFeatureComponent: ConnectionsFeatureComponent? = null

        @Synchronized
        fun initAndGet(dependencies: ConnectionsFeatureDependencies): ConnectionsFeatureComponent {
            if (_connectionsFeatureComponent == null)
                _connectionsFeatureComponent = DaggerConnectionsFeatureComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
            return _connectionsFeatureComponent!!
        }

        fun get(): ConnectionsFeatureComponent {
            if (_connectionsFeatureComponent == null)
                throw RuntimeException("ConnectionsFeatureComponent was not initialized. You must call 'initAndGet(dependencies: ConnectionsFeatureDependencies)' method.")
            return _connectionsFeatureComponent!!
        }

        fun reset() {
            _connectionsFeatureComponent = null
        }

    }

    @ConnectionsFeatureScope
    @Component(
        dependencies = [
            CoreComponentsApi::class,
            LocationFeatureApi::class,
            LocationTrackerFeatureApi::class
        ]
    )
    interface ConnectionsFeatureDependenciesComponent : ConnectionsFeatureDependencies
}