package com.girrafeecstud.example_connections_list.connections_list.di

import com.girrafeecstud.core_base.di.IComponentHolder
import com.girrafeecstud.example_connections_list.connections_list.di.component.ConnectionsListFeatureComponent

object ConnectionsListFeatureComponentHolder :
    IComponentHolder<ConnectionsListFeatureApi, ConnectionsListFeatureDependencies> {

    private var connectionsListFeatureComponent: ConnectionsListFeatureComponent? = null

    @Synchronized
    override fun init(dependencies: ConnectionsListFeatureDependencies) {
        if (connectionsListFeatureComponent == null)
            connectionsListFeatureComponent = ConnectionsListFeatureComponent.initAndGet(dependencies = dependencies)
    }

    override fun get(): ConnectionsListFeatureApi {
        return getComponent()
    }

    internal fun getComponent(): ConnectionsListFeatureComponent {
        checkNotNull(connectionsListFeatureComponent) {
            ("ConnectionsListFeatureComponent is not initialized. You must call 'init(dependencies: ConnectionsListFeatureDependencies)' method")
        }
        return connectionsListFeatureComponent!!
    }

    override fun reset() {
        connectionsListFeatureComponent = null
    }
}