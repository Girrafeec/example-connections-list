/* Created by Girrafeec */

package com.girrafeecstud.location_tracker_impl.data

import android.location.Location
import com.girrafeecstud.core_components_api.DispatcherProvider
import com.girrafeecstud.location_tracker_api.data.ILocationTrackerClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.take
import javax.inject.Inject

class OneTimeLocationTrackerClient @Inject constructor(
    private val client: DefaultLocationTrackerClient,
    private val dispatchers: DispatcherProvider
) : ILocationTrackerClient {

    override fun getLocationUpdates(): Flow<Location> =
        client.getLocationUpdates().take(1).flowOn(dispatchers.io)
}