/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_list.di.component

import com.girrafeecstud.example_connections_list.connections_list.di.annotation.ConnectionsListScope
import com.girrafeecstud.example_connections_list.connections_list.di.module.ConnectionsListModule
import com.girrafeecstud.example_connections_list.connections_list.ui.ConnectionsWithDistanceListFragment
import dagger.Subcomponent

@ConnectionsListScope
@Subcomponent(
    modules = [ConnectionsListModule::class]
)
interface ConnectionsListComponent {

    fun inject(fragment: ConnectionsWithDistanceListFragment)

    @Subcomponent.Builder
    interface Builder {

        fun build(): ConnectionsListComponent

    }

}