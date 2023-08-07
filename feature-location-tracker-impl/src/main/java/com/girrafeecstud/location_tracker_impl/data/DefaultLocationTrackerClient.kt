/* Created by Girrafeec */

package com.girrafeecstud.location_tracker_impl.data

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import com.girrafeecstud.core_base.base.GpsIsNotEnabledException
import com.girrafeecstud.core_base.base.LocationPermissionsNotGrantedException
import com.girrafeecstud.location_tracker_api.data.ILocationTrackerClient
import com.girrafeecstud.location_tracker_api.utils.TrackerPermissionsUtility
import com.girrafeecstud.location_tracker_impl.utils.TrackerUtility
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DefaultLocationTrackerClient @Inject constructor(
    private val context: Context,
    private val client: FusedLocationProviderClient
) : ILocationTrackerClient {

    @SuppressLint("MissingPermission")
    private val _locationUpdates: Flow<Location> = callbackFlow {
        if (!TrackerPermissionsUtility.locationPermissionsGranted(context))
            throw LocationPermissionsNotGrantedException()

        val locationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val isGpsEnabled =
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val isNetworkEnabled =
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        if (!isGpsEnabled && !isNetworkEnabled)
            throw GpsIsNotEnabledException()

        val locationRequest =
            LocationRequest.Builder(TrackerUtility.DEFAULT_LOCATION_TRACKER_INTERVAL)
                .setPriority(TrackerUtility.DEFAULT_LOCATION_TRACKER_PRIORITY)
                .setMinUpdateIntervalMillis(TrackerUtility.MIN_LOCATION_TRACKER_INTERVAL)
                .build()

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.locations.lastOrNull()?.let { location ->
                    launch {
                        send(location)
                    }
                }
                super.onLocationResult(locationResult)
            }
        }

        client.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )

        awaitClose {
            client.removeLocationUpdates(locationCallback)
        }
    }.flowOn(Dispatchers.IO) // TODO create app dispatchers and make di with it

    override fun getLocationUpdates(): Flow<Location> = _locationUpdates

}