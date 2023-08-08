/* Created by Girrafeec */

package com.girrafeecstud.dependency_coordinator_impl

import android.content.Context
import com.girrafeecstud.core_components_api.di.CoreComponentsApi
import com.girrafeecstud.core_components_impl.di.CoreComponentsComponent
import com.girrafeecstud.core_components_impl.di.ICoreComponentsDependencies
import javax.inject.Inject

class CoreComponentsProvider @Inject constructor(
    private val context: Context
) {

    fun getCoreComponentsComponent(): CoreComponentsApi =
        CoreComponentsComponent.initAndGet(dependencies = CoreComponentsDependencies())

    private inner class CoreComponentsDependencies : ICoreComponentsDependencies {
        override val applicationContext: Context = context
    }

}