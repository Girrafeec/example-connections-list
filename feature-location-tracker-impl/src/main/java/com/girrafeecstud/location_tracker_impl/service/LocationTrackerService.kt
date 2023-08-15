/* Created by Girrafeec */

package com.girrafeecstud.location_tracker_impl.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.core_base.domain.base.handleResult
import com.girrafeecstud.core_components_api.DispatcherProvider
import com.girrafeecstud.location_tracker_impl.di.LocationTrackerFeatureComponent
import com.girrafeecstud.location_tracker_api.domain.IGetLastKnownLocationUseCase
import com.girrafeecstud.location_tracker_impl.di.LocationTrackerFeatureComponentHolder
import com.girrafeecstud.location_tracker_impl.engine.LocationTrackerState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject


class LocationTrackerService : Service() {

    private val binder = LocationTrackerBinder()

    @Inject
    internal lateinit var getLastKnownLocationUseCase: IGetLastKnownLocationUseCase

    @Inject
    internal lateinit var dispatchers: DispatcherProvider

    private var _locationTrackerServiceScope: CoroutineScope? = null

    private val locationTrackerServiceScope get() = _locationTrackerServiceScope!!

    companion object {
        private var _state: MutableStateFlow<LocationTrackerState> = MutableStateFlow(
            LocationTrackerState()
        )
        private val state = _state.asStateFlow()
    }

    override fun onCreate() {
        LocationTrackerFeatureComponentHolder.getComponent().inject(this)
        super.onCreate()
        _locationTrackerServiceScope = CoroutineScope(kotlinx.coroutines.SupervisorJob() + dispatchers.io)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        startLocationTracker()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        locationTrackerServiceScope.cancel()
        LocationTrackerFeatureComponentHolder.reset()
    }

    override fun onBind(p0: Intent?): IBinder {
        return binder
    }

    private fun startLocationTracker() {
        getLastKnownLocationUseCase()
            .onEach { result ->
                result.handleResult(
                    onSuccess = { location ->
                        _state.update { it.copy(location = location) }
                    }
                )
            }
            .launchIn(locationTrackerServiceScope)
    }

    inner class LocationTrackerBinder : Binder() {
        fun getService() = this@LocationTrackerService
    }
}