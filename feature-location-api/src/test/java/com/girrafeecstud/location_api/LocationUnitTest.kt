/* Created by Girrafeec */

package com.girrafeecstud.location_api

import com.girrafeecstud.location_api.data.getFormattedDistanceStringTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class LocationUnitTest(
    private val originalDistance: Double,
    private val expectedFormattedDistanceString: String
) {

    companion object {

        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf(2456.0, "2.5 km"),
            arrayOf(934.12, "934 m"),
            arrayOf(4224.48, "4.2 km"),
            arrayOf(999.99, "999 m"),
            arrayOf(1000.0, "1 km"),
            arrayOf(1.0, "1 m"),
            arrayOf(0.0, "0 m"),
            arrayOf(1500.0, "1.5 km")
        )

    }

    @Test
    fun `Distance formatting works as expected`() {
        val actualFormattedDistanceString = originalDistance.getFormattedDistanceStringTest()
        assertEquals(expectedFormattedDistanceString, actualFormattedDistanceString)
    }

}