/* Created by Girrafeec */

package com.girrafeecstud.location_tracker_impl.data.datasource

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.location_api.domain.Location
import com.girrafeecstud.location_tracker_api.data.ILocationTrackerDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import javax.inject.Inject

class OneTimeLocationTrackerDataSource @Inject constructor(
    private val dataSource: DefaultLocationTrackerDataSource
) : ILocationTrackerDataSource {

        override fun getLastKnownLocation(): Flow<BusinessResult<Location>> =
        dataSource.getLastKnownLocation().take(1)
}