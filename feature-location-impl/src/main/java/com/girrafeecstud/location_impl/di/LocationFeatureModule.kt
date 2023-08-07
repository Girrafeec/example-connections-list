/* Created by Girrafeec */

package com.girrafeecstud.location_impl.di

import com.girrafeecstud.location_api.data.IDistanceCalculator
import com.girrafeecstud.location_impl.data.DistanceCalculator
import dagger.Binds
import dagger.Module

@Module
interface LocationFeatureModule {

    @Binds
    @LocationFeatureScope
    fun bindDistanceCalculator(impl: DistanceCalculator): IDistanceCalculator

}