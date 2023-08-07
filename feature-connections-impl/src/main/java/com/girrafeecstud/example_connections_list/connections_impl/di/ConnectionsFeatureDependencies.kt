package com.girrafeecstud.example_connections_list.connections_impl.di

import android.content.Context
import com.girrafeecstud.location_api.data.IDistanceCalculator
import com.girrafeecstud.location_tracker_api.data.ILocationTrackerDataSource

interface ConnectionsFeatureDependencies {

    fun getContext(): Context

    fun getDistanceCalculator(): IDistanceCalculator

    fun getLocationTrackerDataSource(): ILocationTrackerDataSource

}
