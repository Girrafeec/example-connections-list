/* Created by Girrafeec */

package com.girrafeecstud.dependency_coordinator_impl.di

import android.content.Context
import com.girrafeecstud.dependency_coordinator_api.IUnitCreationRequestHandler
import com.girrafeecstud.dependency_coordinator_impl.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DependencyCoordinatorModule.ComponentCoordinatorBindModule::class])
class DependencyCoordinatorModule {

    @Provides
    @DependencyCoordinatorScope
    fun provideCoreComponentsProvider(context: Context) = CoreComponentsProvider(context = context)

    @Provides
    @DependencyCoordinatorScope
    fun provideFeatureComponentsProvider(coreComponentsProvider: CoreComponentsProvider) =
        FeatureComponentsProvider(coreComponentsProvider = coreComponentsProvider)

//    @Provides
//    @DependencyCoordinatorScope
//    fun provideUnitCreationRequestObserver(
//        unitCreationRequestProvider: IUnitCreationRequestProvider,
//        featureComponentsProvider: FeatureComponentsProvider
//    ) = UnitCreationRequestObserver(
//        unitCreationRequestProvider = unitCreationRequestProvider,
//        featureComponentsProvider = featureComponentsProvider
//    )

    @Module
    interface ComponentCoordinatorBindModule {

        @Binds
        @DependencyCoordinatorScope
        fun bindUnitCreationRequestProvider(impl: UnitCreationRequestProvider): IUnitCreationRequestProvider

        @Binds
        @DependencyCoordinatorScope
        fun bindUnitCreationRequestHandler(impl: UnitCreationRequestHandler): IUnitCreationRequestHandler

    }

}