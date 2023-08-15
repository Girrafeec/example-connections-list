package com.girrafeecstud.location_api

import com.girrafeecstud.core_base.base.removeTrailingZeros
import com.girrafeecstud.core_base.base.roundTo


/* Method takes Double distance in meters
** And returns Pair of distance and it's unit. */
fun Double.getFormattedDistanceStringWithUnitFromDistanceInMeters(): Pair<String, DistanceUnit> {
    val unit: DistanceUnit
    val formattedDistance: String

    if (this < 1000) {
        val roundedDistance = this.roundTo(0)
        // For case when distance is rounded from 999 to 1000
        formattedDistance = if (roundedDistance > 999)
            (roundedDistance / 1000).toString().removeTrailingZeros()
        else
            roundedDistance.toString().removeTrailingZeros()
        // For case when distance is rounded from 999 to 1000
        unit = if (roundedDistance > 999) {
            DistanceUnit.KILOMETERS
        } else
            DistanceUnit.METERS
    }
    else {
        formattedDistance = (this / 1000).roundTo(1).toString().removeTrailingZeros()
        unit = DistanceUnit.KILOMETERS
    }

    return Pair(formattedDistance, unit)
}

enum class DistanceUnit {
    KILOMETERS,
    METERS
}