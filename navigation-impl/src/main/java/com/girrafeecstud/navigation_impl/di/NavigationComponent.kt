/* Created by Girrafeec */

package com.girrafeecstud.navigation_impl.di

import com.girrafeecstud.dependency_coordinator_api.di.DependencyCoordinatorApi
import com.girrafeecstud.navigation_api.di.NavigationApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [NavigationModule::class],
    dependencies = [NavigationDependencies::class]
)
interface NavigationComponent : NavigationApi {

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: NavigationDependencies): Builder

        fun build(): NavigationComponent

    }

    companion object {

        private var _navigationComponent: NavigationComponent? = null

        fun initAndGet(dependencies: NavigationDependencies): NavigationComponent {
            if (_navigationComponent == null)
                _navigationComponent = DaggerNavigationComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
            return _navigationComponent!!
        }

        fun get(): NavigationComponent {
            if (_navigationComponent == null)
                throw RuntimeException("NavigationComponent was not initialized. You must call 'initAndGet()' method.")
            return _navigationComponent!!
        }

        fun reset() {
            _navigationComponent = null
        }

    }

    @Singleton
    @Component(
        dependencies = [
            DependencyCoordinatorApi::class
        ]
    )
    interface NavigationDependenciesComponent : NavigationDependencies

}