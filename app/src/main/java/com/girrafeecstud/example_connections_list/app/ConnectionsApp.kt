/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.app

import android.app.Application
import android.content.Context
import com.girrafeecstud.core_components_impl.di.CoreComponentsComponent
import com.girrafeecstud.core_components_impl.di.ICoreComponentsDependencies
import com.girrafeecstud.example_connections_list.di.AppComponent
import com.girrafeecstud.navigation_impl.di.NavigationComponent

class ConnectionsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        AppComponent.initAndGet()
        AppComponent.get().inject(this)

        CoreComponentsComponent.initAndGet(CoreComponentsDependencies())

        NavigationComponent.initAndGet()

    }

    private inner class CoreComponentsDependencies : ICoreComponentsDependencies {
        override val applicationContext: Context = this@ConnectionsApp
    }

}