/* Created by Girrafeec */

package com.girrafeecstud.location_tracker_impl.engine

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import com.girrafeecstud.location_tracker_api.engine.ILocationTrackerEngine
import com.girrafeecstud.location_tracker_impl.service.LocationTrackerService
import javax.inject.Inject

class LocationTrackerEngine @Inject constructor() : ILocationTrackerEngine {

    override fun startLocationTracker(context: Context) {
        if (isLocationTrackerServiceRunning(context = context))
            return
        val locationTrackerServiceIntent = Intent(context, LocationTrackerService::class.java)
        context.startService(locationTrackerServiceIntent)
    }

    private fun isLocationTrackerServiceRunning(context: Context): Boolean {
        val activityManager: ActivityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        return activityManager.getRunningServices(Integer.MAX_VALUE).any {
            it.service.className == LocationTrackerService::class.java.name
        }
    }

}