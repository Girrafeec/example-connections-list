/* Created by Girrafeec */

package com.girrafeecstud.location_api.util

import com.girrafeecstud.core_base.base.roundTo
import com.girrafeecstud.location_api.domain.Location
import kotlin.random.Random

object LocationUtils {

    const val earthRadiusInKilometers = 6371.0

    const val earthRadiusInMeters = earthRadiusInKilometers * 1000.0

    private const val latitudeMin = -90.0

    private const val latitudeMax = 90.0

    private const val longitudeMin = -180.0

    private const val longitudeMax = 180.0

    /* Method returns random location generated within input latitude and longitude parameters.
    ** Result is rounded to 6 decimal signs because it gives accuracy of 0.111 m. */
    fun generateRandomLocation(
        newLocationLatitudeMin: Double = latitudeMin,
        newLocationLatitudeMax: Double = latitudeMax,
        newLocationLongitudeMin: Double = longitudeMin,
        newLocationLongitudeMax: Double = longitudeMax
    ): Location {
        val latitude =
            Random.nextDouble(newLocationLatitudeMin, newLocationLatitudeMax)
                .roundTo(6)
        val longitude =
            Random.nextDouble(newLocationLongitudeMin, newLocationLongitudeMax)
                .roundTo(6)
        return Location(latitude, longitude)
    }

}