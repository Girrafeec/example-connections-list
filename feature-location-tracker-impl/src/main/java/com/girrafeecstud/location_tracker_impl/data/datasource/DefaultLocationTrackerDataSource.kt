/* Created by Girrafeec */

package com.girrafeecstud.location_tracker_impl.data.datasource

import com.girrafeecstud.location_tracker_api.data.ILocationTrackerClient
import com.girrafeecstud.core_base.base.ExceptionType
import com.girrafeecstud.core_base.base.GpsIsNotEnabledException
import com.girrafeecstud.core_base.base.LocationPermissionsNotGrantedException
import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.location_api.domain.Location
import com.girrafeecstud.location_tracker_api.data.ILocationTrackerDataSource
import com.girrafeecstud.location_tracker_impl.di.annotation.DefaultLocationTrackerClientQualifier
import com.girrafeecstud.location_tracker_impl.di.annotation.OneTimeLocationTrackerClientQualifier
import com.girrafeecstud.location_tracker_impl.extensions.uniqueLocation
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DefaultLocationTrackerDataSource @Inject constructor(
    @DefaultLocationTrackerClientQualifier
    private val client: ILocationTrackerClient
) : ILocationTrackerDataSource {

        override fun getLastKnownLocation(): Flow<BusinessResult<Location>> =
            channelFlow {
                val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
                client.getLocationUpdates()
                    .catch { exception ->
                        when (exception) {
                            is LocationPermissionsNotGrantedException -> {
                                send(BusinessResult.Exception(exception = ExceptionType.LOCATION_PERMISSIONS_NOT_GRANTED))
                            }
                            is GpsIsNotEnabledException -> {
                                send(BusinessResult.Exception(exception = ExceptionType.GPS_NOT_ENABLED))
                            }
                        }
                    }
                    .uniqueLocation()
                    .onEach { location ->
                        val location = Location(
                            latitude = location.latitude,
                            longitude = location.longitude
                        )
                        send(BusinessResult.Success(data = location))
                    }
                    .launchIn(scope)
                awaitClose()
            }

}