/* Created by Girrafeec */

package com.girrafeecstud.location_tracker_impl.di

import com.girrafeecstud.core_components_api.di.CoreComponentsApi
import com.girrafeecstud.location_tracker_api.di.LocationTrackerFeatureApi
import com.girrafeecstud.location_tracker_impl.di.annotation.LocationTrackerScope
import com.girrafeecstud.location_tracker_impl.di.dependencies.LocationTrackerDependencies
import com.girrafeecstud.location_tracker_impl.service.LocationTrackerService
import dagger.Component

@LocationTrackerScope
@Component(
    modules = [LocationTrackerModule::class],
    dependencies = [LocationTrackerDependencies::class]
)
interface LocationTrackerFeatureComponent : LocationTrackerFeatureApi {

    fun inject(service: LocationTrackerService)

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: LocationTrackerDependencies): Builder

        fun build(): LocationTrackerFeatureComponent

    }

    companion object {

        private var _locationTrackerFeatureComponent: LocationTrackerFeatureComponent? = null

        @Synchronized
        fun initAndGet(dependencies: LocationTrackerDependencies): LocationTrackerFeatureComponent {
            if (_locationTrackerFeatureComponent == null)
                _locationTrackerFeatureComponent = DaggerLocationTrackerFeatureComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
            return _locationTrackerFeatureComponent!!
        }

        fun get(): LocationTrackerFeatureComponent {
            if (_locationTrackerFeatureComponent == null)
                throw RuntimeException("LocationTrackerFeatureComponent was not initialized. You must call 'initAndGet()' method.")
            return _locationTrackerFeatureComponent!!
        }

        fun reset() {
            _locationTrackerFeatureComponent = null
        }

    }

    @LocationTrackerScope
    @Component(
        dependencies = [
            CoreComponentsApi::class
        ]
    )
    interface LocationTrackerFeatureDependenciesComponent : LocationTrackerDependencies

}