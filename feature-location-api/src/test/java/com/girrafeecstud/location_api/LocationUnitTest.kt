/* Created by Girrafeec */

package com.girrafeecstud.location_api

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class LocationUnitTest(
    private val originalDistance: Double,
    private val expectedFormattedDistanceWithUnit: Pair<String, DistanceUnit>
) {

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(2456.0, Pair("2.5", DistanceUnit.KILOMETERS)),
            arrayOf(934.12, Pair("934", DistanceUnit.METERS)),
            arrayOf(4224.48, Pair("4.2", DistanceUnit.KILOMETERS)),
            arrayOf(999.49, Pair("999", DistanceUnit.METERS)),
            arrayOf(999.65, Pair("1", DistanceUnit.KILOMETERS)),
            arrayOf(999.99, Pair("1", DistanceUnit.KILOMETERS)),
            arrayOf(99.99, Pair("100", DistanceUnit.METERS)),
            arrayOf(9.93, Pair("10", DistanceUnit.METERS)),
            arrayOf(9.4, Pair("9", DistanceUnit.METERS)),
            arrayOf(9.6, Pair("10", DistanceUnit.METERS)),
            arrayOf(1000.0, Pair("1", DistanceUnit.KILOMETERS)),
            arrayOf(1.0, Pair("1", DistanceUnit.METERS)),
            arrayOf(0.0, Pair("0", DistanceUnit.METERS)),
            arrayOf(1500.0, Pair("1.5", DistanceUnit.KILOMETERS))
        )

    }

    @Test
    fun `Distance formatting works as expected`() {
        val actualFormattedDistanceString = originalDistance.getFormattedDistanceStringWithUnitFromDistanceInMeters()
        assertEquals(expectedFormattedDistanceWithUnit, actualFormattedDistanceString)
    }

}