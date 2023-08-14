/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_impl.data

import com.girrafeecstud.core_base.base.removeAt
import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.example_connections_list.connections_api.data.IConnectionsDataSource
import com.girrafeecstud.example_connections_list.connections_api.domain.repository.IConnectionsWithDistanceRepository
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.ConnectionWithDistance
import com.girrafeecstud.location_api.data.IDistanceCalculator
import com.girrafeecstud.location_tracker_api.data.ILocationTrackerDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ConnectionsWithDistanceRepository @Inject constructor(
    private val connectionsDataSource: IConnectionsDataSource,
    private val distanceCalculator: IDistanceCalculator,
    private val locationTrackerDataSource: ILocationTrackerDataSource
) : IConnectionsWithDistanceRepository {

//    override fun getConnectionsWithDistanceToUser(): Flow<BusinessResult<List<ConnectionWithDistance>>> =
//        combine(
//            connectionsDataSource.getConnections(),
//            locationTrackerDataSource.getLastKnownLocation()
//        ) { connectionsResult, locationTrackerResult ->
//            Pair(connectionsResult, locationTrackerResult)
//
//        }.map { (connectionsResult, locationTrackerResult) ->
//            when (connectionsResult) {
//                is BusinessResult.Success -> {
//                    when (locationTrackerResult) {
//                        is BusinessResult.Success -> {
//                            val connections = connectionsResult.data!!
//                            val connectionsWithDistance = connections.map { connection ->
//                                val distance = distanceCalculator
//                                    .calculateDistanceBetweenLocationsInMeters(
//                                        firstLocation = connection.location,
//                                        secondLocation = locationTrackerResult.data!!
//                                    )
//                                ConnectionWithDistance(
//                                    connection = connection,
//                                    distance = distance
//                                )
//                            }
//                            BusinessResult.Success(data = connectionsWithDistance)
//                        }
//                        is BusinessResult.Error -> {
//                            locationTrackerResult
//                        }
//                        is BusinessResult.Exception -> {
//                            val connections = connectionsResult.data!!
//                            val connectionsWithDistance = connections.map { connection ->
//                                ConnectionWithDistance(
//                                    connection = connection,
//                                    distance = null
//                                )
//                            }
//                            BusinessResult.Success(data = connectionsWithDistance)
//                            locationTrackerResult
//                        }
//                    }
//                }
//                is BusinessResult.Error -> connectionsResult
//                is BusinessResult.Exception -> connectionsResult
//            }
//        }

    // TODO make extension functions and remove when operator
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
                                    val connectionsWithDistance = connections.map { connection ->
                                        val distance = distanceCalculator
                                            .calculateDistanceBetweenLocationsInMeters(
                                                firstLocation = connection.location,
                                                secondLocation = locationTrackerResult.data!!
                                            )
                                        ConnectionWithDistance(
                                            connection = connection,
                                            distance = distance
                                        )
                                    }
                                    emit(BusinessResult.Success(data = connectionsWithDistance))
                                }
                                is BusinessResult.Error -> emit(locationTrackerResult)
                                is BusinessResult.Exception -> {
                                    val connections = connectionsResult.data!!
                                    val connectionsWithDistance = connections.map { connection ->
                                        ConnectionWithDistance(
                                            connection = connection,
                                            distance = null
                                        )
                                    }
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
        }

    // TODO make extension functions and remove when operator
    override fun getConnectionsWithDistanceToChosenConnection(
        chosenConnection: Connection
    ): Flow<BusinessResult<List<ConnectionWithDistance>>> =
        connectionsDataSource.getConnections()
            .map { connectionsResult ->
                when (connectionsResult) {
                    is BusinessResult.Success -> {
                        var connections = connectionsResult.data!!

                        // Chosen connection  must not be emmited
                        val indexToRemove = connections.indexOfFirst {
                            it.connectionId == chosenConnection.connectionId
                        }
                        if (indexToRemove != -1) {
                            connections = connections.removeAt(indexToRemove)
                        }

                        val connectionsWithDistance = connections.map { connection ->
                            val distance = distanceCalculator
                            .calculateDistanceBetweenLocationsInMeters(
                                firstLocation = connection.location,
                                secondLocation = chosenConnection.location
                            )
                            ConnectionWithDistance(
                                connection = connection,
                                distance = distance
                            )
                        }
                        BusinessResult.Success(data = connectionsWithDistance)
                    }
                    is BusinessResult.Error -> {
                        connectionsResult
                    }
                    is BusinessResult.Exception -> {
                        connectionsResult
                    }
                }
            }
}