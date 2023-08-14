/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.di

import android.content.Context
import com.girrafeecstud.dependency_coordinator_api.di.DependencyCoordinatorApi
import com.girrafeecstud.dependency_coordinator_impl.IUnitCreationRequestProvider
import com.girrafeecstud.dependency_coordinator_impl.UnitCreationRequestHandler
import com.girrafeecstud.dependency_coordinator_impl.UnitCreationRequestObserver
import com.girrafeecstud.dependency_coordinator_impl.UnitCreationRequestProvider
import com.girrafeecstud.example_connections_list.app.ConnectionsApp
import com.girrafeecstud.example_connections_list.ui.MainActivity
import com.girrafeecstud.navigation_api.di.NavigationApi
import com.girrafeecstud.navigation_impl.navigator.FlowNavigator
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {

//    fun inject(activity: MainActivity)

    fun inject(application: ConnectionsApp)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

    }

    companion object {

        private var _appComponent: AppComponent? = null

        fun initAndGet(): AppComponent {
            if (_appComponent == null)
                _appComponent = DaggerAppComponent
                    .builder()
                    .build()
            return _appComponent!!
        }

        fun get(): AppComponent {
            if (_appComponent == null)
                throw RuntimeException("AppComponent is not initialized. Call 'initAndGet()' method.")
            return _appComponent!!
        }

    }

}