/* Created by Girrafeec */

package com.girrafeecstud.location_tracker_impl.data.repository


import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.core_components_api.DispatcherProvider
import com.girrafeecstud.location_api.domain.Location
import com.girrafeecstud.location_tracker_api.data.ILocationTrackerDataSource
import com.girrafeecstud.location_tracker_impl.di.annotation.DefaultLocationTrackerDataSourceQualifier
import com.girrafeecstud.location_tracker_impl.domain.repository.ILocationTrackerRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LocationTrackerRepository @Inject constructor(
    @DefaultLocationTrackerDataSourceQualifier
    private val locationTrackerDataSource: ILocationTrackerDataSource,
    private val dispatchers: DispatcherProvider
) : ILocationTrackerRepository {

    override fun getLastKnownLocation(): Flow<BusinessResult<Location>> =
        locationTrackerDataSource.getLastKnownLocation().flowOn(dispatchers.io)
}