/* Created by Girrafeec */

package com.girrafeecstud.location_impl.data

import com.girrafeecstud.core_base.base.roundTo
import com.girrafeecstud.location_api.data.IDistanceCalculator
import com.girrafeecstud.location_api.domain.Location
import com.girrafeecstud.location_api.util.LocationUtils
import javax.inject.Inject
import kotlin.math.*

class DistanceCalculator @Inject constructor() : IDistanceCalculator {

    /* Method calculates distance between two coordinates according to Haversine formula
    ** and returns result rounded to 1 sign after integer part.
    ** Haversine formula can result in an error of up to 0.5%. */
    override fun calculateDistanceBetweenLocationsInMeters(
        firstLocation: Location,
        secondLocation: Location
    ): Double {
        val firstLatitudeInRad = Math.toRadians(firstLocation.latitude)
        val firstLongitudeInRad = Math.toRadians(firstLocation.longitude)
        val secondLatitudeInRad = Math.toRadians(secondLocation.latitude)
        val secondLongitudeInRad = Math.toRadians(secondLocation.longitude)

        val latitudeDelta = secondLatitudeInRad - firstLatitudeInRad
        val longitudeDelta = secondLongitudeInRad - firstLongitudeInRad

        val a = sin(latitudeDelta / 2).pow(2) +
                cos(firstLatitudeInRad) * cos(secondLatitudeInRad) * sin(longitudeDelta / 2).pow(2)

        val result = LocationUtils.earthRadiusInMeters * 2 * asin(sqrt(a))
        return result.roundTo(1)
    }

}