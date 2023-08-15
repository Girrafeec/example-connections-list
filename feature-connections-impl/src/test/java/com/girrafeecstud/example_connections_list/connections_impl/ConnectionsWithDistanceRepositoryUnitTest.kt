/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_impl

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.core_base.domain.base.handleResult
import com.girrafeecstud.core_components_api.TestDispatchers
import com.girrafeecstud.example_connections_list.connections_api.data.IConnectionsDataSource
import com.girrafeecstud.example_connections_list.connections_api.domain.repository.IConnectionsWithDistanceRepository
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.ConnectionWithDistance
import com.girrafeecstud.example_connections_list.connections_impl.data.ConnectionsWithDistanceRepository
import com.girrafeecstud.location_api.data.IDistanceCalculator
import com.girrafeecstud.location_tracker_api.data.ILocationTrackerDataSource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ConnectionsWithDistanceRepositoryUnitTest {

    private lateinit var connectionsWithDistanceRepository: IConnectionsWithDistanceRepository

    private lateinit var connectionsDataSource: IConnectionsDataSource

    private lateinit var locationTrackerDataSource: ILocationTrackerDataSource

    private lateinit var distanceCalculator: IDistanceCalculator

    @Before
    fun setUp() {
        connectionsDataSource = mock()
        locationTrackerDataSource = mock()
        distanceCalculator = mock()


        val testCoroutineDispatcher = UnconfinedTestDispatcher()

        val testDispatchers = TestDispatchers(testDispatcher = testCoroutineDispatcher)

        connectionsWithDistanceRepository = ConnectionsWithDistanceRepository(
            connectionsDataSource = connectionsDataSource,
            locationTrackerDataSource = locationTrackerDataSource,
            distanceCalculator = distanceCalculator,
            dispatchers = testDispatchers
        )
    }

    @Test
    fun `EXPECT connections with distance list when call getConnectionsWithDistanceToUser()`() =
        runBlocking {

            val expectedResult = TestSampleData.connectionsWithDistance

            whenever(
                connectionsDataSource.getConnections()
            ).thenReturn(
                flow {
                    emit(BusinessResult.Success(data = TestSampleData.connections))
                }
            )

            whenever(
                locationTrackerDataSource.getLastKnownLocation()
            ).thenReturn(
                flow {
                    emit(BusinessResult.Success(data = TestSampleData.userLocation))
                }
            )

            whenever(
                distanceCalculator.calculateDistanceBetweenLocationsInMeters(any(), any())
            ).thenAnswer {
                // Remove first element from sample distances list and then return it
                val distance = TestSampleData.distances.removeAt(0)
                distance
            }

            var actualResult: List<ConnectionWithDistance>? = null

            connectionsWithDistanceRepository.getConnectionsWithDistanceToUser()
                .collect { result ->
                    result.handleResult(
                        onSuccess = { connectionsWithDistance ->
                            actualResult = connectionsWithDistance
                        }
                    )
                }

            assertEquals(expectedResult, actualResult)
        }

    @Test
    fun `EXPECT connections list without distance and then GpsIsNotEnabledException`() =
        runBlocking {

            val expectedConnectionsResult = TestSampleData.connectionsWithEmptyDistance
            val expectedExceptionResult = TestSampleData.gpsIsNotEnabledExceptionResult

            whenever(
                connectionsDataSource.getConnections()
            ).thenReturn(
                flow {
                    emit(BusinessResult.Success(data = TestSampleData.connections))
                }
            )

            whenever(
                locationTrackerDataSource.getLastKnownLocation()
            ).thenReturn(
                flow {
                    emit(TestSampleData.gpsIsNotEnabledExceptionResult)
                }
            )

            var actualConnectionsResult: List<ConnectionWithDistance>? = null

            var actualExceptionResult: BusinessResult.Exception? = null

            connectionsWithDistanceRepository.getConnectionsWithDistanceToUser()
                .collect { result ->
                    result.handleResult(
                        onSuccess = { connectionsWithDistance ->
                            actualConnectionsResult = connectionsWithDistance
                        },
                        onException = { exception ->
                            actualExceptionResult = BusinessResult.Exception(exception = exception)
                        }
                    )
                }

            assertEquals(expectedConnectionsResult, actualConnectionsResult)
            assertEquals(expectedExceptionResult, actualExceptionResult)
        }

    @Test
    fun `EXPECT connections list without distance and then LocationPermissionNotGrantedException`() =
        runBlocking {

            val expectedConnectionsResult = TestSampleData.connectionsWithEmptyDistance
            val expectedExceptionResult = TestSampleData.locationPermissionsNotGrantedExceptionResult

            whenever(
                connectionsDataSource.getConnections()
            ).thenReturn(
                flow {
                    emit(BusinessResult.Success(data = TestSampleData.connections))
                }
            )

            whenever(
                locationTrackerDataSource.getLastKnownLocation()
            ).thenReturn(
                flow {
                    emit(TestSampleData.locationPermissionsNotGrantedExceptionResult)
                }
            )

            var actualConnectionsResult: List<ConnectionWithDistance>? = null

            var actualExceptionResult: BusinessResult.Exception? = null

            connectionsWithDistanceRepository.getConnectionsWithDistanceToUser()
                .collect { result ->
                    result.handleResult(
                        onSuccess = { connectionsWithDistance ->
                            actualConnectionsResult = connectionsWithDistance
                        },
                        onException = { exception ->
                            actualExceptionResult = BusinessResult.Exception(exception = exception)
                        }
                    )
                }

            assertEquals(expectedConnectionsResult, actualConnectionsResult)
            assertEquals(expectedExceptionResult, actualExceptionResult)
        }

}