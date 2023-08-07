/* Created by Girrafeec */

package com.girrafeecstud.location_api.data

import com.girrafeecstud.location_api.domain.Location

interface IDistanceCalculator {

    fun calculateDistanceBetweenLocationsInMeters(
        firstLocation: Location,
        secondLocation: Location
    ): Double

}