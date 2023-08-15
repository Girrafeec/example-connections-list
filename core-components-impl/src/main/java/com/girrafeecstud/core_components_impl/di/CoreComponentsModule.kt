/* Created by Girrafeec */

package com.girrafeecstud.core_components_impl.di

import com.girrafeecstud.core_components_api.DispatcherProvider
import com.girrafeecstud.core_components_impl.AppDispatchers
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface CoreComponentsModule {

    @Binds
    @Singleton
    fun bindAppDispatchers(impl: AppDispatchers): DispatcherProvider

}