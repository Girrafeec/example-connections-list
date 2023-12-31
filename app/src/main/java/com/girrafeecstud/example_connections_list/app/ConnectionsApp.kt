/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.app

import android.app.Application
import android.content.Context
import com.girrafeecstud.core_components_impl.di.CoreComponentsComponent
import com.girrafeecstud.core_components_impl.di.ICoreComponentsDependencies
import com.girrafeecstud.example_connections_list.di.AppComponent
import com.girrafeecstud.example_connections_list.di.FeatureComponentsProvider
import com.girrafeecstud.example_connections_list.navigation.NavigationComponent

class ConnectionsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        AppComponent.initAndGet()
//        AppComponent.get().inject(this)

        CoreComponentsComponent.initAndGet(CoreComponentsDependencies())

        NavigationComponent.initAndGet()

        /* NavigationComponent requires start destination set from XML file
        ** that's why it is necessary to init first screen feature component
        ** before MainActivity inflates its view */
        FeatureComponentsProvider.initConnectionsListFeatureComponent()
    }

    private inner class CoreComponentsDependencies : ICoreComponentsDependencies {
        override val applicationContext: Context = this@ConnectionsApp
    }

}