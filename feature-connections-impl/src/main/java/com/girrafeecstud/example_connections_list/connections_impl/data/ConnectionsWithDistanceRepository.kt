/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_impl.data

import com.girrafeecstud.core_base.base.mapSuccess
import com.girrafeecstud.core_base.base.removeAt
import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.core_components_api.DispatcherProvider
import com.girrafeecstud.example_connections_list.connections_api.data.IConnectionsDataSource
import com.girrafeecstud.example_connections_list.connections_api.domain.repository.IConnectionsWithDistanceRepository
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.ConnectionWithDistance
import com.girrafeecstud.location_api.data.IDistanceCalculator
import com.girrafeecstud.location_api.domain.Location
import com.girrafeecstud.location_tracker_api.data.ILocationTrackerDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ConnectionsWithDistanceRepository @Inject constructor(
    private val connectionsDataSource: IConnectionsDataSource,
    private val distanceCalculator: IDistanceCalculator,
    private val locationTrackerDataSource: ILocationTrackerDataSource,
    private val dispatchers: DispatcherProvider
) : IConnectionsWithDistanceRepository {

    override fun getConnectionsWithDistanceToUser(): Flow<BusinessResult<List<ConnectionWithDistance>>> =
        flow {
            val connectionsFlow = connectionsDataSource.getConnections()
            val locationTrackerFlow = locationTrackerDataSource.getLastKnownLocation()
            connectionsFlow.collect {  connectionsResult ->
                when (connectionsResult) {
                    is BusinessResult.Success -> {
                        locationTrackerFlow.collect { locationTrackerResult ->
                            when (locationTrackerResult) {
                                is BusinessResult.Success -> {
                                    val connections = connectionsResult.data!!
                                    val connectionsWithDistance = calculateConnectionsWithDistanceToUser(
                                        connections = connections,
                                        userLocation = locationTrackerResult.data
                                    )
                                    emit(BusinessResult.Success(data = connectionsWithDistance))
                                }
                                is BusinessResult.Error -> emit(locationTrackerResult)
                                is BusinessResult.Exception -> {
                                    val connections = connectionsResult.data!!
                                    val connectionsWithDistance = calculateConnectionsWithDistanceToUser(
                                        connections = connections,
                                        userLocation = null
                                    )
                                    emit(BusinessResult.Success(data = connectionsWithDistance))
                                    emit(locationTrackerResult)
                                }
                            }
                        }
                    }
                    is BusinessResult.Error -> emit(connectionsResult)
                    is BusinessResult.Exception -> emit(connectionsResult)
                }
            }
        }.flowOn(dispatchers.io)

    override fun getConnectionsWithDistanceToChosenConnection(
        chosenConnection: Connection
    ): Flow<BusinessResult<List<ConnectionWithDistance>>> =
        connectionsDataSource.getConnections()
            .mapSuccess { connections ->
                calculateConnectionsWithDistanceToChosenConnections(
                    chosenConnection = chosenConnection,
                    connections = connections
                )
            }.flowOn(dispatchers.io)

    private fun calculateConnectionsWithDistanceToUser(
        connections: List<Connection>,
        userLocation: Location?
    ): List<ConnectionWithDistance> {

        val connectionsWithDistance = connections.map { connection ->
            val distance = if (userLocation == null)
                null
            else
                distanceCalculator.calculateDistanceBetweenLocationsInMeters(
                    firstLocation = connection.location,
                    secondLocation = userLocation
                )
            ConnectionWithDistance(
                connection = connection,
                distance = distance
            )
        }

        return connectionsWithDistance
    }

    private fun calculateConnectionsWithDistanceToChosenConnections(
        chosenConnection: Connection,
        connections: List<Connection>
    ): List<ConnectionWithDistance> {

        // Chosen connection must not be emitted
        val filteredConnections = connections.filterNot {
            it.connectionId == chosenConnection.connectionId
        }

        val connectionsWithDistance = filteredConnections.map { connection ->
            val distance = distanceCalculator.calculateDistanceBetweenLocationsInMeters(
                firstLocation = connection.location,
                secondLocation = chosenConnection.location
            )
            ConnectionWithDistance(
                connection = connection,
                distance = distance
            )
        }

        return connectionsWithDistance
    }
}