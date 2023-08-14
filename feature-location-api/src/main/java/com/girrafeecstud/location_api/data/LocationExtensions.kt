package com.girrafeecstud.location_api.data

import android.content.Context
import com.girrafeecstud.core_base.base.removeTrailingZeros
import com.girrafeecstud.core_base.base.roundTo
import com.girrafeecstud.location_api.R


// TODO rewrite method and do smth with test form!
//fun Double.getFormattedDistanceString(context: Context): String {
//    val formattedDistance = when {
//        this < 1000 -> {
//            context.resources.getString(
//                R.string.distance_in_meters,
//                this.roundTo(0).toString().removeTrailingZeros()
//            )
//        }
//        else -> {
//            context.resources.getString(
//                R.string.distance_in_km,
//                (this / 1000).roundTo(1).toString().removeTrailingZeros()
//            )
//        }
//    }
//    return formattedDistance
//}

fun Double.getFormattedDistanceStringTest(): String {
    val formattedDistance = when {
        this < 1000 -> {
            if (this == this.toInt().toDouble())
                this.toInt().toString() + " m"
            else
                this.roundTo(0).toString().removeTrailingZeros() + " m"
        }
        else -> {
            (this / 1000).roundTo(1).toString().removeTrailingZeros() + " km"
        }
    }
    return formattedDistance
}