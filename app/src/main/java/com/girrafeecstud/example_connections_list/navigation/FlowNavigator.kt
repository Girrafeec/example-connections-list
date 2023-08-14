/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.navigation

import android.util.Log
import androidx.navigation.NavController
import com.girrafeecstud.example_connections_list.R
import com.girrafeecstud.navigation_api.INavigator
import com.girrafeecstud.navigation_api.setStartDestination
import javax.inject.Inject

class FlowNavigator @Inject constructor(
) : INavigator<FlowDestination> {

    private var navController: NavController? = null

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun navigateToDestination(destination: FlowDestination) {
        checkNotNull(navController){
            "NavController is not initialized. Call 'setNavController(navController: NavController)' method."
        }

        setUpComponentForDestination(destination = destination)
        when (destination) {
            is FlowDestination.ConnectionsListFlow -> {
                navController?.navigate(R.id.connections_list_flow_fragment)
            }
        }
    }

    override fun setStartDestination(destination: FlowDestination) {
        checkNotNull(navController){
            "NavController is not initialized. Call 'setNavController(navController: NavController)' method."
        }
        setUpComponentForDestination(destination = destination)
        navController?.setStartDestination(
            destination = destination,
            graphId = R.navigation.main_nav_flow,
            args = destination.args
        )
    }

    private fun setUpComponentForDestination(destination: FlowDestination) {
        when (destination) {
            is FlowDestination.ConnectionsListFlow -> {
                com.girrafeecstud.example_connections_list.di.FeatureComponentsProvider.initConnectionsListFeatureComponent()
            }
        }
    }

}