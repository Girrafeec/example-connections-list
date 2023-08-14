/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_impl.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import com.girrafeecstud.core_base.base.ExceptionType
import com.girrafeecstud.core_base.base.launchPeriodically
import com.girrafeecstud.core_base.base.mapToCommonError
import com.girrafeecstud.core_base.domain.base.handleResult
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection
import com.girrafeecstud.example_connections_list.connections_api.domain.usecase.IGetConnectionsWithDistanceToChosenConnectionUseCase
import com.girrafeecstud.example_connections_list.connections_api.domain.usecase.IGetConnectionsWithDistanceToUserUseCase
import com.girrafeecstud.example_connections_list.connections_api.engine.ConnectionsWithDistanceEngineState
import com.girrafeecstud.example_connections_list.connections_impl.di.ConnectionsFeatureComponent
import com.girrafeecstud.example_connections_list.connections_impl.util.ConnectionsFeatureUtils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ConnectionsWithDistanceService : Service() {

    companion object {
        private var _state: MutableStateFlow<ConnectionsWithDistanceEngineState> =
            MutableStateFlow(ConnectionsWithDistanceEngineState())

        val state = _state.asStateFlow()
    }

    private var currentMode: String? = null

    private val binder = ConnectionsWithDistanceServiceBinder()

    private var serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private var isConnectionsRequestEnabled = false

    @Inject
    lateinit var getConnectionsWithDistanceToChosenConnectionUseCase: IGetConnectionsWithDistanceToChosenConnectionUseCase

    @Inject
    lateinit var getConnectionsWithDistanceToUserUseCase: IGetConnectionsWithDistanceToUserUseCase

    override fun onCreate() {
        super.onCreate()
        ConnectionsFeatureComponent.get().inject(service = this)
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    inner class ConnectionsWithDistanceServiceBinder : Binder() {
        fun getService() = this@ConnectionsWithDistanceService
    }

    override fun onBind(intent: Intent?): IBinder = binder

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        intent?.let { lIntent ->
            val connectionsWithDistanceEngineMode =
                lIntent.getStringExtra(ConnectionsFeatureUtils.CONNECTIONS_WITH_DISTANCE_SERVICE_MODE)

            connectionsWithDistanceEngineMode?.let { mode ->
                Log.i("tag", "mode $mode")
                if (currentMode != mode) {
                    currentMode = mode
                    stopRequests()
                    isConnectionsRequestEnabled = true
                    _state.update {
                        it.copy(isLoading = true)
                    }
                    // TODO fix!!!
                    serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
                    serviceScope.launchPeriodically(
                        repeatMillis = ConnectionsFeatureUtils.CONNECTIONS_REQUEST_TIMEOUT,
                        isEnabled = { isConnectionsRequestEnabled },
                        action = { startRequests(intent = lIntent) }
                    )
                }
            }
        }

        return START_STICKY
    }

    private fun startRequests(intent: Intent) {
        when (currentMode) {
            ConnectionsFeatureUtils.CONNECTIONS_WITH_DISTANCE_TO_USER_MODE -> {
                getConnectionsWithDistanceToUser()
            }
            ConnectionsFeatureUtils.CONNECTIONS_WITH_DISTANCE_TO_CHOSEN_CONNECTION_MODE -> {
                @Suppress("DEPRECATION")
                val chosenConnection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                    intent.getParcelableExtra(
                        ConnectionsFeatureUtils.CHOSEN_CONNECTION,
                        Connection::class.java
                    )
                else
                    intent.getParcelableExtra(ConnectionsFeatureUtils.CHOSEN_CONNECTION)
                chosenConnection?.let { connection ->
                    getConnectionsWithDistanceToChosenConnection(chosenConnection = connection)
                }
            }
        }
    }

    private fun stopRequests() {
        isConnectionsRequestEnabled = false
        serviceScope.cancel()
    }

    private fun getConnectionsWithDistanceToUser() {
        serviceScope.launch {
            getConnectionsWithDistanceToUserUseCase()
                .onEach { result ->
                    Log.i("tag", "result to user: $result")
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    result.handleResult(
                        onSuccess = { connectionsWithDistance ->
                            _state.update {
                                it.copy(connections = connectionsWithDistance)
                            }
                        },
                        onException = { exception ->
                            when (exception) {
                                ExceptionType.GPS_NOT_ENABLED ->
//                                    if (!_state.value.isGpsNeeded)
                                        _state.update {
                                            it.copy(isGpsNeeded = true)
                                        }
                                ExceptionType.LOCATION_PERMISSIONS_NOT_GRANTED ->
                                     /* For not showing special message all the time while service is trying
                                     to get list with distances
                                      */
//                                    if (!_state.value.isLocationPermissionNeeded)
                                        _state.update {
                                            it.copy(isLocationPermissionNeeded = true)
                                        }
                            }
                        }
                    )
                }
                .launchIn(serviceScope)
        }
    }

    private fun getConnectionsWithDistanceToChosenConnection(chosenConnection: Connection) {
        serviceScope.launch {
            getConnectionsWithDistanceToChosenConnectionUseCase(chosenConnection = chosenConnection)
                .onEach { result ->
                    Log.i("tag", "result to chosen: $result")
                    _state.update {
                        it.copy(isLoading = false)
                    }
                    result.handleResult(
                        onSuccess = { connectionsWithDistance ->
                            _state.update {
                                it.copy(
                                    connections = connectionsWithDistance,
                                    isGpsNeeded = false,
                                    isLocationPermissionNeeded = false,
                                    error = null
                                )
                            }
                        },
                        onException = { exception ->
                            when (exception) {
                                ExceptionType.GPS_NOT_ENABLED ->
                                    _state.update {
                                        it.copy(isGpsNeeded = true)
                                    }
                                ExceptionType.LOCATION_PERMISSIONS_NOT_GRANTED ->
                                    _state.update {
                                        it.copy(isLocationPermissionNeeded = true)
                                    }
                            }
                        }
                    )
                }
                .launchIn(serviceScope)
        }
    }

}