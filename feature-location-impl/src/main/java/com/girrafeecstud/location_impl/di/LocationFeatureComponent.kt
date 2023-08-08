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

        @Synchronized
        fun initAndGet(): LocationFeatureComponent {
            if (_locationFeatureComponent == null)
                _locationFeatureComponent = DaggerLocationFeatureComponent
                    .builder()
                    .build()
            return _locationFeatureComponent!!
        }

        fun get(): LocationFeatureComponent {
            if (_locationFeatureComponent == null)
                throw RuntimeException("LocationFeatureComponent was not initialized. You must call 'initAndGet()' method.")
            return _locationFeatureComponent!!
        }

        fun reset() {
            _locationFeatureComponent = null
        }

    }

}