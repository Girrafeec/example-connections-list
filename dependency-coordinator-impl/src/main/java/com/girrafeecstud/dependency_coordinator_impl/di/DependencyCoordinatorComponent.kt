/* Created by Girrafeec */

package com.girrafeecstud.dependency_coordinator_impl.di

import android.content.Context
import com.girrafeecstud.dependency_coordinator_api.di.DependencyCoordinatorApi
import com.girrafeecstud.dependency_coordinator_impl.IUnitCreationRequestProvider
import dagger.Component
import javax.inject.Singleton

@DependencyCoordinatorScope
@Component(
    modules = [DependencyCoordinatorModule::class],
    dependencies = [DependencyCoordinatorDependencies::class]
)
interface DependencyCoordinatorComponent : DependencyCoordinatorApi {

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: DependencyCoordinatorDependencies): Builder

        fun build(): DependencyCoordinatorComponent

    }

    companion object {

        private var _dependencyCoordinatorComponent: DependencyCoordinatorComponent? = null

        fun initAndGet(dependencies: DependencyCoordinatorDependencies): DependencyCoordinatorComponent {
            if (_dependencyCoordinatorComponent == null)
                _dependencyCoordinatorComponent = DaggerDependencyCoordinatorComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
            return _dependencyCoordinatorComponent!!
        }

        fun get(): DependencyCoordinatorComponent {
            if (_dependencyCoordinatorComponent == null)
                throw RuntimeException("DependencyCoordinatorComponent was not initialized. You must call 'initAndGet()' method.")
            return _dependencyCoordinatorComponent!!
        }

        fun reset() {
            _dependencyCoordinatorComponent = null
        }

    }

}