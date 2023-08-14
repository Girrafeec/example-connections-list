/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_list.di.module

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.girrafeecstud.core_base.di.ViewModelKey
import com.girrafeecstud.example_connections_list.connections_list.di.annotation.ConnectionsListScope
import com.girrafeecstud.example_connections_list.connections_list.presentation.ConnectionsWithDistanceViewModel
import com.girrafeecstud.example_connections_list.connections_list.ui.ConnectionWithDistanceViewHolder
import com.girrafeecstud.example_connections_list.connections_list.ui.ConnectionsWithDistanceAdapter
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ConnectionsListModule {

    @Binds
    @ConnectionsListScope
    @IntoMap
    @ViewModelKey(ConnectionsWithDistanceViewModel::class)
    fun bindConnectionsWithDistanceViewModel(impl: ConnectionsWithDistanceViewModel): ViewModel

    @Binds
    @ConnectionsListScope
    fun bindConnectionsWithDistanceAdapter(impl: ConnectionsWithDistanceAdapter):
            RecyclerView.Adapter<ConnectionWithDistanceViewHolder>

}