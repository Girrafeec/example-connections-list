/* Created by Girrafeec */

package com.girrafeecstud.location_tracker_impl.di.dependencies

import android.content.Context
import com.girrafeecstud.core_base.di.IComponentDependencies
import retrofit2.Retrofit

interface LocationTrackerDependencies : IComponentDependencies {

    fun getContext(): Context

}