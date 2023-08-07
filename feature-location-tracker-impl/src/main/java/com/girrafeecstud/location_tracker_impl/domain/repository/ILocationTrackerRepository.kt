/* Created by Girrafeec */

package com.girrafeecstud.location_tracker_impl.domain.repository

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.location_api.domain.Location
import kotlinx.coroutines.flow.Flow

interface ILocationTrackerRepository {

    fun getLastKnownLocation(): Flow<BusinessResult<Location>>

}