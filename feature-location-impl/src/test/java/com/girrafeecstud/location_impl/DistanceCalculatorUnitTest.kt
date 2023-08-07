/* Created by Girrafeec */

package com.girrafeecstud.location_impl

import com.girrafeecstud.location_api.domain.Location
import com.girrafeecstud.location_impl.data.DistanceCalculator
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class DistanceCalculatorUnitTest(
    private val firstLocation: Location,
    private val secondLocation: Location,
    private val expectedDistance: Double
) {

    private lateinit var distanceCalculator: DistanceCalculator

    @Before
    fun setUp() {
        distanceCalculator = DistanceCalculator()
    }

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(
                Location(59.9343, 30.3351),
                Location(59.9500, 30.3167),
                2029.0
            ),
            arrayOf(
                Location(59.9343, 30.3351),
                Location(59.9210, 30.3200),
                1705.0
            ),
            arrayOf(
                Location(59.9343, 30.3351),
                Location(59.9390, 30.3158),
                1199.0
            ),
            arrayOf(
                Location(59.9343, 30.3351),
                Location(59.9271, 30.3331),
                809.0
            ),
            arrayOf(
                Location(59.9343, 30.3351),
                Location(59.9425, 30.3338),
                916.0
            )
        )

    }

    @Test
    fun `Distance calculation works as expected`() {
        val actualDistance = distanceCalculator.calculateDistanceBetweenLocationsInMeters(
            firstLocation = firstLocation,
            secondLocation = secondLocation
        )
        assertEquals(expectedDistance, actualDistance, 10.0)
    }

}