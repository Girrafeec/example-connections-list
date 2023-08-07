/* Created by Girrafeec */

package com.girrafeecstud.location_tracker_impl.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.location_tracker_impl.di.LocationTrackerFeatureComponent
import com.girrafeecstud.location_tracker_api.domain.IGetLastKnownLocationUseCase
import com.girrafeecstud.location_tracker_impl.engine.LocationTrackerState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class LocationTrackerService : Service() {

    private val locationTrackerServiceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val binder = LocationTrackerBinder()

    @Inject
    internal lateinit var getLastKnownLocationUseCase: IGetLastKnownLocationUseCase

    companion object {
        private var _state: MutableStateFlow<LocationTrackerState> = MutableStateFlow(
            LocationTrackerState()
        )
        private val state = _state.asStateFlow()
    }

    override fun onCreate() {
        LocationTrackerFeatureComponent.locationTrackerFeatureComponent.inject(this)
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        startLocationTracker()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        locationTrackerServiceScope.cancel()
    }

    override fun onBind(p0: Intent?): IBinder {
        return binder
    }

    private fun startLocationTracker() {
        getLastKnownLocationUseCase()
            .onEach { result ->
                when (result) {
                    is BusinessResult.Success -> {
                        _state.update { it.copy(location = result.data) }
                    }
                    is BusinessResult.Exception-> {
                    }
                    is BusinessResult.Error -> {
                    }
                }
            }
            .launchIn(locationTrackerServiceScope)
    }

    inner class LocationTrackerBinder : Binder() {
        fun getService() = this@LocationTrackerService
    }
}