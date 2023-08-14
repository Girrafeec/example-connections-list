package com.girrafeecstud.example_connections_list.connections_list.ui

import com.girrafeecstud.example_connections_list.connections_api.domain.entity.Connection

interface ConnectionClickEvent {

    fun onConnectionClicked(connection: Connection)

}
