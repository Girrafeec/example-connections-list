/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.di

import com.girrafeecstud.example_connections_list.app.ConnectionsApp
import com.girrafeecstud.example_connections_list.navigation.FlowNavigator
import com.girrafeecstud.example_connections_list.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface AppComponent {

    fun inject(activity: MainActivity)

    fun flowNavigator(): FlowNavigator

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