/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_list.presentation

import com.girrafeecstud.core_ui.presentation.BaseComponentViewModel
import com.girrafeecstud.example_connections_list.connections_list.di.component.ConnectionsListComponent
import com.girrafeecstud.example_connections_list.connections_list.di.component.ConnectionsListFeatureComponent

class ConnectionsListComponentViewModel : BaseComponentViewModel() {

    private var _connectionsListComponent: ConnectionsListComponent? = null

    fun get(): ConnectionsListComponent {
        if (_connectionsListComponent == null)
            throw RuntimeException("ConnectionsListComponent is not initialized. You must call 'initComponent()' method")
        return _connectionsListComponent!!
    }

    override fun initComponent() {
        _connectionsListComponent =
            ConnectionsListFeatureComponent.get()
                .connectionsListComponent()
                .build()
    }

    override fun destroyComponent() {
        _connectionsListComponent = null
    }
}