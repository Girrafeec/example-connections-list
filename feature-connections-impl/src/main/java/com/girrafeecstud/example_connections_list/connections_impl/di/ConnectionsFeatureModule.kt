/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_impl.di

import com.girrafeecstud.example_connections_list.connections_api.data.IConnectionsDataSource
import com.girrafeecstud.example_connections_list.connections_api.domain.repository.IConnectionsRepository
import com.girrafeecstud.example_connections_list.connections_api.domain.repository.IConnectionsWithDistanceRepository
import com.girrafeecstud.example_connections_list.connections_api.domain.usecase.IGetConnectionsWithDistanceToChosenConnectionUseCase
import com.girrafeecstud.example_connections_list.connections_api.domain.usecase.IGetConnectionsWithDistanceToUserUseCase
import com.girrafeecstud.example_connections_list.connections_api.engine.IConnectionsWithDistanceEngine
import com.girrafeecstud.example_connections_list.connections_impl.data.ConnectionsLocalDataSource
import com.girrafeecstud.example_connections_list.connections_impl.data.ConnectionsRepository
import com.girrafeecstud.example_connections_list.connections_impl.data.ConnectionsWithDistanceRepository
import com.girrafeecstud.example_connections_list.connections_impl.domain.GetConnectionsWithDistanceToChosenConnectionUseCase
import com.girrafeecstud.example_connections_list.connections_impl.domain.GetConnectionsWithDistanceToUserUseCase
import com.girrafeecstud.example_connections_list.connections_impl.engine.ConnectionsWithDistanceEngine
import dagger.Binds
import dagger.Module

@Module
interface ConnectionsFeatureModule {

    @Binds
    @ConnectionsFeatureScope
    fun bindConnectionsLocalDataSource(impl: ConnectionsLocalDataSource): IConnectionsDataSource

    @Binds
    @ConnectionsFeatureScope
    fun bindConnectionsRepository(impl: ConnectionsRepository): IConnectionsRepository

    @Binds
    @ConnectionsFeatureScope
    fun bindConnectionsWithDistanceRepository(
        impl: ConnectionsWithDistanceRepository
    ): IConnectionsWithDistanceRepository

    @Binds
    @ConnectionsFeatureScope
    fun bindGetConnectionsWithDistanceToUserUseCase(
        impl: GetConnectionsWithDistanceToUserUseCase
    ): IGetConnectionsWithDistanceToUserUseCase

    @Binds
    @ConnectionsFeatureScope
    fun bindGetConnectionsWithDistanceToChosenConnectionUseCase(
        impl: GetConnectionsWithDistanceToChosenConnectionUseCase
    ): IGetConnectionsWithDistanceToChosenConnectionUseCase

    @Binds
    @ConnectionsFeatureScope
    fun bindConnectionsWithDistanceEngine(impl: ConnectionsWithDistanceEngine): IConnectionsWithDistanceEngine

}