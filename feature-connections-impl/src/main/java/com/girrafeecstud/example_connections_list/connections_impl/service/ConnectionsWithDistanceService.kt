/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_impl.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
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

    private val binder = ConnectionsWithDistanceServiceBinder()

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

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
                if (mode == ConnectionsFeatureUtils.CONNECTIONS_WITH_DISTANCE_TO_USER_MODE) {
                    getConnectionsWithDistanceToUser()
                }
                if (mode == ConnectionsFeatureUtils.CONNECTIONS_WITH_DISTANCE_TO_CHOSEN_CONNECTION_MODE) {
                    @Suppress("DEPRECATION")
                    val chosenConnection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                        lIntent.getParcelableExtra(
                            ConnectionsFeatureUtils.CHOSEN_CONNECTION,
                            Connection::class.java
                        )
                    else
                        lIntent.getParcelableExtra(ConnectionsFeatureUtils.CHOSEN_CONNECTION)

                    chosenConnection?.let { connection ->
                        getConnectionsWithDistanceToChosenConnection(chosenConnection = connection)
                    }
                }
            }
        }

        return START_STICKY
    }

    private fun getConnectionsWithDistanceToUser() {
        serviceScope.launch {
            getConnectionsWithDistanceToUserUseCase()
                .onStart {
                    _state.update {
                        it.copy(isLoading = true)
                    }
                }
                .onEach { result ->
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
                            _state.update {
                                it.copy(error = exception.mapToCommonError())
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
                .onStart {
                    _state.update {
                        it.copy(isLoading = true)
                    }
                }
                .onEach { result ->
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
                            _state.update {
                                it.copy(error = exception.mapToCommonError())
                            }
                        }
                    )
                }
                .launchIn(serviceScope)
        }
    }

}