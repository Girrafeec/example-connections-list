/* Created by Girrafeec */

package com.girrafeecstud.core_components_impl.di

import com.girrafeecstud.core_components_api.di.CoreComponentsApi
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CoreComponentsModule::class],
    dependencies = [ICoreComponentsDependencies::class]
)
interface CoreComponentsComponent : CoreComponentsApi {

    @Component.Builder
    interface Builder {

        fun dependencies(dependencies: ICoreComponentsDependencies): Builder

        fun build(): CoreComponentsComponent

    }

    companion object {

        private var _coreComponentsComponent: CoreComponentsComponent? = null

        @Synchronized
        fun initAndGet(dependencies: ICoreComponentsDependencies): CoreComponentsComponent {
            if (_coreComponentsComponent == null)
                _coreComponentsComponent = DaggerCoreComponentsComponent
                    .builder()
                    .dependencies(dependencies = dependencies)
                    .build()
            return _coreComponentsComponent!!
        }

        fun get(): CoreComponentsComponent {
            checkNotNull(_coreComponentsComponent) {
                ("CoreComponentsComponent is not initialized. You must call 'dependencies: ICoreComponentsDependencies)' method.")
            }
            return _coreComponentsComponent!!
        }

        fun reset() {
            _coreComponentsComponent = null
        }

    }

}