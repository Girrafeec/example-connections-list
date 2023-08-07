/* Created by Girrafeec */

package com.girrafeecstud.location_tracker_api.di

import com.girrafeecstud.location_tracker_api.data.ILocationTrackerDataSource
import com.girrafeecstud.location_tracker_api.domain.IGetLastKnownLocationUseCase
import com.girrafeecstud.location_tracker_api.engine.ILocationTrackerEngine

interface LocationTrackerFeatureApi {

    fun getLocationTrackerDataSource(): ILocationTrackerDataSource

    fun getLastKnownLocationUseCase(): IGetLastKnownLocationUseCase

    fun getLocationTrackerEngine(): ILocationTrackerEngine

}