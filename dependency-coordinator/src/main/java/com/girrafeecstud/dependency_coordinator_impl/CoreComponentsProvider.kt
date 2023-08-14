/* Created by Girrafeec */

package com.girrafeecstud.dependency_coordinator_impl

import android.content.Context
import com.girrafeecstud.core_components_api.di.CoreComponentsApi
import com.girrafeecstud.core_components_impl.di.CoreComponentsComponent
import com.girrafeecstud.core_components_impl.di.ICoreComponentsDependencies
import javax.inject.Inject

class CoreComponentsProvider {

    companion object {
        fun getCoreComponentsComponent(): CoreComponentsApi =
            CoreComponentsComponent.get()

    }

}