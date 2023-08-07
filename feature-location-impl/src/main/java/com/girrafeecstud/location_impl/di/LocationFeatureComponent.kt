/* Created by Girrafeec */

package com.girrafeecstud.location_impl.di

import com.girrafeecstud.location_api.di.LocationFeatureApi
import dagger.Component

@LocationFeatureScope
@Component(
    modules = [LocationFeatureModule::class]
)
interface LocationFeatureComponent : LocationFeatureApi {

    @Component.Builder
    interface Builder {

        fun build(): LocationFeatureComponent

    }

    companion object {

        private var _locationFeatureComponent: LocationFeatureComponent? = null

        val locationFeatureComponent: LocationFeatureComponent
            get() {
                if (_locationFeatureComponent == null)
                    init()
                return _locationFeatureComponent!!
            }

        fun init() {
            if (_locationFeatureComponent == null)
                _locationFeatureComponent = DaggerLocationFeatureComponent
                    .builder()
                    .build()
        }

        fun reset() {
            _locationFeatureComponent = null
        }

    }

}