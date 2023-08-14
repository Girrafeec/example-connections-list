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


        @Synchronized
        fun initAndGet(dependencies: ConnectionsFeatureDependencies): ConnectionsFeatureComponent {
            return DaggerConnectionsFeatureComponent
                .builder()
                .dependencies(dependencies = dependencies)
                .build()
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