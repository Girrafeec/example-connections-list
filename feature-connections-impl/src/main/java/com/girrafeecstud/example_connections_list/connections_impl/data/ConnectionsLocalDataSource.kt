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
                firstName = "Александр",
                lastName = "Соколов",
                profileImageUrl = "https://images.unsplash.com/photo-1588641657136-a2b97ca9512a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                firstName = "Алина",
                lastName = "Баева",
                profileImageUrl = "https://images.unsplash.com/photo-1556964218-61ae5bf37ad1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                firstName = "Никита",
                lastName = "Новиков",
                profileImageUrl = "https://images.unsplash.com/photo-1623629267708-d79c838741ec?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                firstName = "Евгений",
                lastName = "Соколов",
                profileImageUrl = "https://plus.unsplash.com/premium_photo-1661499572301-135097f24bf0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                firstName = "Владимир",
                lastName = "Фёдоров",
                profileImageUrl = "https://images.unsplash.com/photo-1617191523489-6b4ef7232fca?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                firstName = "Николай",
                lastName = "Дорохов",
                profileImageUrl = "https://images.unsplash.com/photo-1586897414837-468be47328c4?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                firstName = "Светлана",
                lastName = "Петрова",
                profileImageUrl = "https://images.unsplash.com/photo-1583001810263-fd44b051736e?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                firstName = "Александра",
                lastName = "Кривицкая",
                profileImageUrl = "https://images.unsplash.com/photo-1557063430-fa540276834c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                firstName = "Мария",
                lastName = "Светова",
                profileImageUrl = "https://images.unsplash.com/photo-1557080326-2da057667a82?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                firstName = "Сергей",
                lastName = "Попов",
                profileImageUrl = "https://images.unsplash.com/photo-1626306340609-34ffc0164bf1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                firstName = "Екатерина",
                lastName = "Брежнева",
                profileImageUrl = "https://images.unsplash.com/photo-1623198362241-eaf7e6999f75?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                location = generateRandomLocationInStPetersburg()
            ),
            Connection(
                firstName = "Алексей",
                lastName = "Савинов",
                profileImageUrl = "https://images.unsplash.com/photo-1579064211320-66c8ed1cd426?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
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