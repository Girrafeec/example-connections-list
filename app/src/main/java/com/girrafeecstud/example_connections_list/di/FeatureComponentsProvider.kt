/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.di

import com.girrafeecstud.example_connections_list.connections_api.di.ConnectionsFeatureApi
import com.girrafeecstud.example_connections_list.connections_impl.di.ConnectionsFeatureComponentHolder
import com.girrafeecstud.example_connections_list.connections_impl.di.DaggerConnectionsFeatureComponent_ConnectionsFeatureDependenciesComponent
import com.girrafeecstud.example_connections_list.connections_list.di.ConnectionsListFeatureComponentHolder
import com.girrafeecstud.example_connections_list.connections_list.di.component.DaggerConnectionsListFeatureComponent_ConnectionsListFeatureDependenciesComponent
import com.girrafeecstud.location_api.di.LocationFeatureApi
import com.girrafeecstud.location_impl.di.LocationFeatureComponent
import com.girrafeecstud.location_tracker_api.di.LocationTrackerFeatureApi
import com.girrafeecstud.location_tracker_impl.di.DaggerLocationTrackerFeatureComponent_LocationTrackerFeatureDependenciesComponent
import com.girrafeecstud.location_tracker_impl.di.LocationTrackerFeatureComponentHolder
import com.girrafeecstud.example_connections_list.navigation.NavigationComponent

class FeatureComponentsProvider {

    companion object {
        fun getLocationFeatureComponent(): LocationFeatureApi =
            LocationFeatureComponent.initAndGet()

        fun getLocationTrackerFeatureComponent(): LocationTrackerFeatureApi {
            LocationTrackerFeatureComponentHolder.init(
                dependencies = DaggerLocationTrackerFeatureComponent_LocationTrackerFeatureDependenciesComponent
                    .builder()
                    .coreComponentsApi(CoreComponentsProvider.getCoreComponentsComponent())
                    .build()
            )
            return LocationTrackerFeatureComponentHolder.get()
        }


        fun getConnectionsFeatureComponent(): ConnectionsFeatureApi {
            ConnectionsFeatureComponentHolder.init(
                dependencies = DaggerConnectionsFeatureComponent_ConnectionsFeatureDependenciesComponent
                    .builder()
                    .coreComponentsApi(CoreComponentsProvider.getCoreComponentsComponent())
                    .locationFeatureApi(getLocationFeatureComponent())
                    .locationTrackerFeatureApi(getLocationTrackerFeatureComponent())
                    .build()
            )
            return ConnectionsFeatureComponentHolder.get()
        }

        fun initConnectionsListFeatureComponent() {
            ConnectionsListFeatureComponentHolder.init(
                dependencies = DaggerConnectionsListFeatureComponent_ConnectionsListFeatureDependenciesComponent
                    .builder()
                    .connectionsFeatureApi(getConnectionsFeatureComponent())
                    .navigationApi(NavigationComponent.get())
                    .build()
            )
        }
    }

}