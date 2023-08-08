/* Created by Girrafeec */

package com.girrafeecstud.dependency_coordinator_impl.di

import android.content.Context
import com.girrafeecstud.dependency_coordinator_api.IComponentCreationRequestHandler
import com.girrafeecstud.dependency_coordinator_impl.*
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DependencyCoordinatorModule.ComponentCoordinatorBindModule::class])
class DependencyCoordinatorModule {

    @Provides
    @DependencyCoordinatorScope
    fun provideCoreComponentsProvider(context: Context) = CoreComponentsProvider(context = context)

    @Provides
    @DependencyCoordinatorScope
    fun provideFeatureComponentsProvider(coreComponentsProvider: CoreComponentsProvider) =
        FeatureComponentsProvider(coreComponentsProvider = coreComponentsProvider)

    @Module
    interface ComponentCoordinatorBindModule {

        @Binds
        @DependencyCoordinatorScope
        fun bindComponentCreationRequestProvider(impl: ComponentCreationRequestProvider): IComponentCreationRequestProvider

        @Binds
        @DependencyCoordinatorScope
        fun bindComponentCreationRequestHandler(impl: ComponentCreationRequestHandler): IComponentCreationRequestHandler

    }

}