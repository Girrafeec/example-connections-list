/* Created by Girrafeec */

package com.girrafeecstud.dependency_coordinator_impl

import com.girrafeecstud.example_connections_list.connections_api.di.ConnectionsFeatureApi
import com.girrafeecstud.example_connections_list.connections_impl.di.ConnectionsFeatureComponent
import com.girrafeecstud.example_connections_list.connections_impl.di.DaggerConnectionsFeatureComponent_ConnectionsFeatureDependenciesComponent
import com.girrafeecstud.location_api.di.LocationFeatureApi
import com.girrafeecstud.location_impl.di.LocationFeatureComponent
import com.girrafeecstud.location_tracker_api.di.LocationTrackerFeatureApi
import com.girrafeecstud.location_tracker_impl.di.DaggerLocationTrackerFeatureComponent_LocationTrackerFeatureDependenciesComponent
import com.girrafeecstud.location_tracker_impl.di.LocationTrackerFeatureComponent
import javax.inject.Inject

class FeatureComponentsProvider @Inject constructor(
    private val coreComponentsProvider: CoreComponentsProvider
) {

    fun getLocationFeatureComponent(): LocationFeatureApi =
        LocationFeatureComponent.initAndGet()

    fun getLocationTrackerFeatureComponent(): LocationTrackerFeatureApi =
        LocationTrackerFeatureComponent.initAndGet(
            dependencies = DaggerLocationTrackerFeatureComponent_LocationTrackerFeatureDependenciesComponent
                .builder()
                .coreComponentsApi(coreComponentsProvider.getCoreComponentsComponent())
                .build()
        )

    fun getConnectionsFeatureComponent(): ConnectionsFeatureApi =
        ConnectionsFeatureComponent.initAndGet(
            dependencies = DaggerConnectionsFeatureComponent_ConnectionsFeatureDependenciesComponent
                .builder()
                .coreComponentsApi(coreComponentsProvider.getCoreComponentsComponent())
                .locationFeatureApi(getLocationFeatureComponent())
                .locationTrackerFeatureApi(getLocationTrackerFeatureComponent())
                .build()
        )

}