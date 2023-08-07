/* Created by Girrafeec */

package com.girrafeecstud.location_api.di

import com.girrafeecstud.location_api.data.IDistanceCalculator

interface LocationFeatureApi {

    fun getDistanceCalculator(): IDistanceCalculator

}