package com.girrafeecstud.example_connections_list.connections_impl.di

import com.girrafeecstud.core_base.di.IComponentHolder
import com.girrafeecstud.example_connections_list.connections_api.di.ConnectionsFeatureApi

object ConnectionsFeatureComponentHolder :
    IComponentHolder<ConnectionsFeatureApi, ConnectionsFeatureDependencies> {

    private var connectionsFeatureComponent: ConnectionsFeatureComponent? = null

    @Synchronized
    override fun init(dependencies: ConnectionsFeatureDependencies) {
        if (connectionsFeatureComponent == null)
            connectionsFeatureComponent = ConnectionsFeatureComponent.initAndGet(dependencies = dependencies)
    }

    override fun get(): ConnectionsFeatureApi = getComponent()

    internal fun getComponent(): ConnectionsFeatureComponent {
        checkNotNull(connectionsFeatureComponent) {
            ("ConnectionsFeatureComponent was not initialized. You must call 'init(dependencies: ConnectionsFeatureDependencies)' method.")
        }
        return connectionsFeatureComponent!!
    }

    override fun reset() {
        connectionsFeatureComponent = null
    }
}