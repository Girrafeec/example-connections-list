package com.girrafeecstud.example_connections_list.connections_impl

import com.girrafeecstud.core_base.base.ExceptionType
import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection
import com.girrafeecstud.example_connections_list.connections_api.domain.entity.ConnectionWithDistance
import com.girrafeecstud.location_api.domain.Location

object TestSampleData {

    val userLocation = Location(latitude = 59.931128, longitude = 30.359744)

    val connections = listOf(
        Connection(
            connectionId = "e16b668a-3826-11ee-be56-0242ac120002",
            firstName = "Александр",
            lastName = "Соколов",
            profileImageUrl = "https://images.unsplash.com/photo-1588641657136-a2b97ca9512a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            location = Location(latitude = 59.914541, longitude = 30.317981)
        ),
        Connection(
            connectionId = "e16b6982-3826-11ee-be56-0242ac120002",
            firstName = "Алина",
            lastName = "Баева",
            profileImageUrl = "https://images.unsplash.com/photo-1556964218-61ae5bf37ad1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            location = Location(latitude = 59.926773, longitude = 30.344421)
        ),
        Connection(
            connectionId = "e16b6aea-3826-11ee-be56-0242ac120002",
            firstName = "Никита",
            lastName = "Новиков",
            profileImageUrl = "https://images.unsplash.com/photo-1623629267708-d79c838741ec?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            location = Location(latitude = 59.912881, longitude = 30.349417)
        ),
        Connection(
            connectionId = "e16b6c5c-3826-11ee-be56-0242ac120002",
            firstName = "Евгений",
            lastName = "Соколов",
            profileImageUrl = "https://plus.unsplash.com/premium_photo-1661499572301-135097f24bf0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            location = Location(latitude = 59.925615, longitude = 30.316444)
        ),
    )

    val distances = mutableListOf(
        2978.0,
        984.7,
        2113.0,
        2498.0
    )

    val connectionsWithDistance = listOf(
        ConnectionWithDistance(
            connection = Connection(
                connectionId = "e16b668a-3826-11ee-be56-0242ac120002",
                firstName = "Александр",
                lastName = "Соколов",
                profileImageUrl = "https://images.unsplash.com/photo-1588641657136-a2b97ca9512a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                location = Location(latitude = 59.914541, longitude = 30.317981)
            ),
            distance = 2978.0
        ),
        ConnectionWithDistance(
            connection = Connection(
                connectionId = "e16b6982-3826-11ee-be56-0242ac120002",
                firstName = "Алина",
                lastName = "Баева",
                profileImageUrl = "https://images.unsplash.com/photo-1556964218-61ae5bf37ad1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                location = Location(latitude = 59.926773, longitude = 30.344421)
            ),
            distance = 984.7
        ),
        ConnectionWithDistance(
            connection = Connection(
                connectionId = "e16b6aea-3826-11ee-be56-0242ac120002",
                firstName = "Никита",
                lastName = "Новиков",
                profileImageUrl = "https://images.unsplash.com/photo-1623629267708-d79c838741ec?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                location = Location(latitude = 59.912881, longitude = 30.349417)
            ),
            distance = 2113.0
        ),
        ConnectionWithDistance(
            connection = Connection(
                connectionId = "e16b6c5c-3826-11ee-be56-0242ac120002",
                firstName = "Евгений",
                lastName = "Соколов",
                profileImageUrl = "https://plus.unsplash.com/premium_photo-1661499572301-135097f24bf0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                location = Location(latitude = 59.925615, longitude = 30.316444)
            ),
            distance = 2498.0
        )
    )

    val connectionsWithEmptyDistance =
        listOf(
            ConnectionWithDistance(
                connection = Connection(
                    connectionId = "e16b668a-3826-11ee-be56-0242ac120002",
                    firstName = "Александр",
                    lastName = "Соколов",
                    profileImageUrl = "https://images.unsplash.com/photo-1588641657136-a2b97ca9512a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    location = Location(latitude = 59.914541, longitude = 30.317981)
                ),
                distance = null
            ),
            ConnectionWithDistance(
                connection = Connection(
                    connectionId = "e16b6982-3826-11ee-be56-0242ac120002",
                    firstName = "Алина",
                    lastName = "Баева",
                    profileImageUrl = "https://images.unsplash.com/photo-1556964218-61ae5bf37ad1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    location = Location(latitude = 59.926773, longitude = 30.344421)
                ),
                distance = null
            ),
            ConnectionWithDistance(
                connection = Connection(
                    connectionId = "e16b6aea-3826-11ee-be56-0242ac120002",
                    firstName = "Никита",
                    lastName = "Новиков",
                    profileImageUrl = "https://images.unsplash.com/photo-1623629267708-d79c838741ec?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    location = Location(latitude = 59.912881, longitude = 30.349417)
                ),
                distance = null
            ),
            ConnectionWithDistance(
                connection = Connection(
                    connectionId = "e16b6c5c-3826-11ee-be56-0242ac120002",
                    firstName = "Евгений",
                    lastName = "Соколов",
                    profileImageUrl = "https://plus.unsplash.com/premium_photo-1661499572301-135097f24bf0?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                    location = Location(latitude = 59.925615, longitude = 30.316444)
                ),
                distance = null
            )
        )

    val gpsIsNotEnabledExceptionResult =
        BusinessResult.Exception(exception = ExceptionType.GPS_NOT_ENABLED)

    val locationPermissionsNotGrantedExceptionResult =
        BusinessResult.Exception(exception = ExceptionType.LOCATION_PERMISSIONS_NOT_GRANTED)

}