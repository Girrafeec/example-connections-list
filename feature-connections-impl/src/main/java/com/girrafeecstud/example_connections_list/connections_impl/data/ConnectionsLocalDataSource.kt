/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_impl.data

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.example_connections_list.connections_api.data.IConnectionsDataSource
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection
import com.girrafeecstud.location_api.domain.Location
import com.girrafeecstud.location_api.util.LocationUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ConnectionsLocalDataSource @Inject constructor(

) : IConnectionsDataSource {

    companion object {
        private const val stPetersburgLatitudeMin = 59.835783
        private const val stPetersburgLatitudeMax = 59.999291
        private const val stPetersburgLongitudeMin = 30.201356
        private const val stPetersburgLongitudeMax = 30.513386
    }

    private fun getConnectionsWithRandomLocation(): List<Connection> =
        listOf(
            Connection(
                connectionId = "e16b668a-3826-11ee-be56-0242ac120002",
                firstName = "Александр",
                lastName = "Соколов",
                profileImageUrl = "https://images.unsplash.com/photo-1588641657136-a2b97ca9512a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                connectionId = "e16b6982-3826-11ee-be56-0242ac120002",
                firstName = "Алина",
                lastName = "Баева",
                profileImageUrl = "https://images.unsplash.com/photo-1556964218-61ae5bf37ad1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=884&q=80",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                connectionId = "e16b6aea-3826-11ee-be56-0242ac120002",
                firstName = "Никита",
                lastName = "Новиков",
                profileImageUrl = "https://images.unsplash.com/photo-1623629267708-d79c838741ec?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=687&q=80",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                connectionId = "e16b6c5c-3826-11ee-be56-0242ac120002",
                firstName = "Евгений",
                lastName = "Соколов",
                profileImageUrl = "https://images.unsplash.com/photo-1528142704126-8b5aa26aa25e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=735&q=80",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                connectionId = "e16b6dba-3826-11ee-be56-0242ac120002",
                firstName = "Владимир",
                lastName = "Фёдоров",
                profileImageUrl = "https://images.unsplash.com/photo-1617191523489-6b4ef7232fca?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=627&q=80",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                connectionId = "e16b7116-3826-11ee-be56-0242ac120002",
                firstName = "Николай",
                lastName = "Дорохов",
                profileImageUrl = "https://images.unsplash.com/photo-1586897414837-468be47328c4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=687&q=80",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                connectionId = "e16b7274-3826-11ee-be56-0242ac120002",
                firstName = "Светлана",
                lastName = "Петрова",
                profileImageUrl = "https://images.unsplash.com/photo-1583001810263-fd44b051736e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=687&q=80",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                connectionId = "e16b7576-3826-11ee-be56-0242ac120002",
                firstName = "Александра",
                lastName = "Кривицкая",
                profileImageUrl = "https://images.unsplash.com/photo-1557063430-fa540276834c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1467&q=80",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                connectionId = "e16b7850-3826-11ee-be56-0242ac120002",
                firstName = "Мария",
                lastName = "Светова",
                profileImageUrl = "https://images.unsplash.com/photo-1557080326-2da057667a82?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=687&q=80",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                connectionId = "e16b79a4-3826-11ee-be56-0242ac120002",
                firstName = "Сергей",
                lastName = "Попов",
                profileImageUrl = "https://images.unsplash.com/photo-1626306340609-34ffc0164bf1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=687&q=80",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                connectionId = "e16b7aee-3826-11ee-be56-0242ac120002",
                firstName = "Екатерина",
                lastName = "Брежнева",
                profileImageUrl = "https://images.unsplash.com/photo-1623198362241-eaf7e6999f75?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=955&q=80",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                connectionId = "e16b8610-3826-11ee-be56-0242ac120002",
                firstName = "Алексей",
                lastName = "Савинов",
                profileImageUrl = "https://images.unsplash.com/photo-1579064211320-66c8ed1cd426?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=687&q=80",
                location = generateRandomLocationInStPetersburg()
            )
        )

    private fun generateRandomLocationInStPetersburg(): Location {
        return LocationUtils.generateRandomLocation(
            newLocationLatitudeMin = stPetersburgLatitudeMin,
            newLocationLatitudeMax = stPetersburgLatitudeMax,
            newLocationLongitudeMin = stPetersburgLongitudeMin,
            newLocationLongitudeMax = stPetersburgLongitudeMax
        )
    }

    override fun getConnections(): Flow<BusinessResult<List<Connection>>> =
        flow {
            emit(BusinessResult.Success(data = getConnectionsWithRandomLocation()))
        }
}