package com.girrafeecstud.location_tracker_impl.di

import com.girrafeecstud.core_base.di.IComponentHolder
import com.girrafeecstud.location_tracker_api.di.LocationTrackerFeatureApi
import com.girrafeecstud.location_tracker_impl.di.dependencies.LocationTrackerDependencies

object LocationTrackerFeatureComponentHolder
    : IComponentHolder<LocationTrackerFeatureApi, LocationTrackerDependencies> {

    private var locationTrackerFeatureComponent: LocationTrackerFeatureComponent? = null

    override fun init(dependencies: LocationTrackerDependencies) {
        if (locationTrackerFeatureComponent == null)
            locationTrackerFeatureComponent = LocationTrackerFeatureComponent.initAndGet(dependencies = dependencies)
    }

    override fun get(): LocationTrackerFeatureApi = getComponent()

    internal fun getComponent(): LocationTrackerFeatureComponent {
        checkNotNull(locationTrackerFeatureComponent) {
            ("LocationTrackerFeatureComponent was not initialized. You must call 'init(dependencies: LocationTrackerDependencies)' method.")
        }
        return locationTrackerFeatureComponent!!
    }

    override fun reset() {
        locationTrackerFeatureComponent = null
    }
}