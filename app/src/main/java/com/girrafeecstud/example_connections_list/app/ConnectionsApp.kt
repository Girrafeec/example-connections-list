/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.app

import android.app.Application
import android.content.Context
import com.girrafeecstud.core_components_api.di.CoreComponentsApi
import com.girrafeecstud.core_components_impl.di.CoreComponentsComponent
import com.girrafeecstud.core_components_impl.di.ICoreComponentsDependencies
import com.girrafeecstud.dependency_coordinator_impl.UnitCreationRequestObserver
import com.girrafeecstud.dependency_coordinator_impl.di.DependencyCoordinatorComponent
import com.girrafeecstud.dependency_coordinator_impl.di.DependencyCoordinatorDependencies
import com.girrafeecstud.example_connections_list.connections_api.di.ConnectionsFeatureApi
import com.girrafeecstud.example_connections_list.connections_impl.di.ConnectionsFeatureComponent
import com.girrafeecstud.example_connections_list.connections_impl.di.DaggerConnectionsFeatureComponent_ConnectionsFeatureDependenciesComponent
import com.girrafeecstud.example_connections_list.connections_list.di.component.ConnectionsListFeatureComponent
import com.girrafeecstud.example_connections_list.connections_list.di.component.DaggerConnectionsListFeatureComponent_ConnectionsListFeatureDependenciesComponent
import com.girrafeecstud.example_connections_list.di.AppComponent
import com.girrafeecstud.location_api.di.LocationFeatureApi
import com.girrafeecstud.location_impl.di.LocationFeatureComponent
import com.girrafeecstud.location_tracker_api.di.LocationTrackerFeatureApi
import com.girrafeecstud.location_tracker_impl.di.DaggerLocationTrackerFeatureComponent_LocationTrackerFeatureDependenciesComponent
import com.girrafeecstud.location_tracker_impl.di.LocationTrackerFeatureComponent
import com.girrafeecstud.navigation_impl.di.DaggerNavigationComponent_NavigationDependenciesComponent
import com.girrafeecstud.navigation_impl.di.NavigationComponent
import javax.inject.Inject

class ConnectionsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        AppComponent.initAndGet()
        AppComponent.get().inject(this)

        DependencyCoordinatorComponent.initAndGet(
            dependencies = DependencyCoordinatorDependenciesImpl()
        )

        NavigationComponent.initAndGet(
            dependencies = DaggerNavigationComponent_NavigationDependenciesComponent
                .builder()
                .dependencyCoordinatorApi(DependencyCoordinatorComponent.get())
                .build()
        )

        ConnectionsListFeatureComponent.initAndGet(
            dependencies = DaggerConnectionsListFeatureComponent_ConnectionsListFeatureDependenciesComponent
                .builder()
                .connectionsFeatureApi(getConnectionsFeatureComponent())
                .navigationApi(NavigationComponent.get())
                .build()
        )
    }

    private fun getCoreComponentsComponent(): CoreComponentsApi =
        CoreComponentsComponent.initAndGet(dependencies = CoreComponentsDependencies())

    private fun getLocationFeatureComponent(): LocationFeatureApi =
        LocationFeatureComponent.initAndGet()

    private fun getLocationTrackerFeatureComponent(): LocationTrackerFeatureApi =
        LocationTrackerFeatureComponent.initAndGet(
            dependencies = DaggerLocationTrackerFeatureComponent_LocationTrackerFeatureDependenciesComponent
                .builder()
                .coreComponentsApi(getCoreComponentsComponent())
                .build()
        )

    private fun getConnectionsFeatureComponent(): ConnectionsFeatureApi =
        ConnectionsFeatureComponent.initAndGet(
            dependencies = DaggerConnectionsFeatureComponent_ConnectionsFeatureDependenciesComponent
                .builder()
                .coreComponentsApi(getCoreComponentsComponent())
                .locationFeatureApi(getLocationFeatureComponent())
                .locationTrackerFeatureApi(getLocationTrackerFeatureComponent())
                .build()
        )

    private inner class DependencyCoordinatorDependenciesImpl : DependencyCoordinatorDependencies {
        override val applicationContext: Context = this@ConnectionsApp
    }

    private inner class CoreComponentsDependencies : ICoreComponentsDependencies {
        override val applicationContext: Context = this@ConnectionsApp
    }

}