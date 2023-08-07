/* Created by Girrafeec */

package com.girrafeecstud.location_tracker_impl.di

import android.content.Context
import com.girrafeecstud.location_tracker_api.data.ILocationTrackerClient
import com.girrafeecstud.location_tracker_api.domain.IGetLastKnownLocationUseCase
import com.girrafeecstud.location_tracker_impl.data.DefaultLocationTrackerClient
import com.girrafeecstud.location_tracker_api.data.ILocationTrackerDataSource
import com.girrafeecstud.location_tracker_api.engine.ILocationTrackerEngine
import com.girrafeecstud.location_tracker_impl.data.OneTimeLocationTrackerClient
import com.girrafeecstud.location_tracker_impl.data.datasource.DefaultLocationTrackerDataSource
import com.girrafeecstud.location_tracker_impl.data.datasource.OneTimeLocationTrackerDataSource
import com.girrafeecstud.location_tracker_impl.data.repository.LocationTrackerRepository
import com.girrafeecstud.location_tracker_impl.di.annotation.*
import com.girrafeecstud.location_tracker_impl.domain.repository.ILocationTrackerRepository
import com.girrafeecstud.location_tracker_impl.domain.usecase.GetLastKnownLocationUseCase
import com.girrafeecstud.location_tracker_impl.engine.LocationTrackerEngine
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [LocationTrackerModule.LocationTrackerBindModule::class]
)
class LocationTrackerModule {

    @Provides
    @LocationTrackerScope
    fun provideFusedLocationClient(applicationContext: Context): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(applicationContext)

    @Module
    interface LocationTrackerBindModule {
        @Binds
        @LocationTrackerScope
        @DefaultLocationTrackerClientQualifier
        fun bindDefaultLocationTrackerClient(impl: DefaultLocationTrackerClient): ILocationTrackerClient

        @Binds
        @LocationTrackerScope
        @OneTimeLocationTrackerClientQualifier
        fun bindOneTimeLocationTrackerClient(impl: OneTimeLocationTrackerClient): ILocationTrackerClient

        @Binds
        @LocationTrackerScope
        @DefaultLocationTrackerDataSourceQualifier
        fun bindDefaultLocationTrackerDataSource(impl: DefaultLocationTrackerDataSource): ILocationTrackerDataSource

        @Binds
        @LocationTrackerScope
        fun bindOneTomeLocationTrackerDataSource(impl: OneTimeLocationTrackerDataSource): ILocationTrackerDataSource

        @Binds
        @LocationTrackerScope
        fun bindLocationTrackerRepository(impl: LocationTrackerRepository): ILocationTrackerRepository

        @Binds
        @LocationTrackerScope
        fun bindGetLastKnownLocationUseCase(impl: GetLastKnownLocationUseCase): IGetLastKnownLocationUseCase

        @Binds
        @LocationTrackerScope
        fun bindLocationTrackerEngine(impl: LocationTrackerEngine): ILocationTrackerEngine

    }
}