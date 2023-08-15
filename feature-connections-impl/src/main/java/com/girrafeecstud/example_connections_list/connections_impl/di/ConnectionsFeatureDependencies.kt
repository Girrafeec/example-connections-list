package com.girrafeecstud.example_connections_list.connections_impl.di

import android.content.Context
import com.girrafeecstud.core_base.di.IComponentDependencies
import com.girrafeecstud.core_components_api.DispatcherProvider
import com.girrafeecstud.location_api.data.IDistanceCalculator
import com.girrafeecstud.location_tracker_api.data.ILocationTrackerDataSource

interface ConnectionsFeatureDependencies : IComponentDependencies {

    fun getContext(): Context

    fun getDispatcherProvider(): DispatcherProvider

    fun getDistanceCalculator(): IDistanceCalculator

    fun getLocationTrackerDataSource(): ILocationTrackerDataSource

}
