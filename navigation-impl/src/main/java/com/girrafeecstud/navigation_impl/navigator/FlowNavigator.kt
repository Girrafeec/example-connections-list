/* Created by Girrafeec */

package com.girrafeecstud.navigation_impl.navigator

import android.util.Log
import androidx.navigation.NavController
import com.girrafeecstud.dependency_coordinator_api.IUnitCreationRequestHandler
import com.girrafeecstud.example_connections_list.connections_list.di.ConnectionsListUnit
import com.girrafeecstud.navigation_api.INavigator
import com.girrafeecstud.navigation_api.setStartDestination
import com.girrafeecstud.navigation_impl.FlowDestination
import com.girrafeecstud.navigation_impl.R
import javax.inject.Inject

class FlowNavigator @Inject constructor(
//    private val unitRequestHandler: IUnitCreationRequestHandler
) : INavigator<FlowDestination> {

    private var _navController: NavController? = null

    private var _unitRequestHandler: IUnitCreationRequestHandler? = null

    fun setNavController(navController: NavController) {
        this._navController = navController
    }

    fun setUnitRequestHandler(unitRequestHandler: IUnitCreationRequestHandler) {
        this._unitRequestHandler = unitRequestHandler
    }

    override fun navigateToDestination(destination: FlowDestination) {
        if (_navController == null)
            throw RuntimeException("NavController is not initialized. Call 'setNavController(navController: NavController)' method.")

//        requestUnitCreationForDestination(destination = destination)

        when (destination) {
            is FlowDestination.ConnectionsListFlow -> {
                _navController?.navigate(R.id.connections_list_flow_fragment)
            }
        }
    }

    override fun setStartDestination(destination: FlowDestination) {
        if (_navController == null)
            throw RuntimeException("NavController is not initialized. Call 'setNavController(navController: NavController)' method.")

//        requestUnitCreationForDestination(destination = destination)

        _navController?.setStartDestination(
            destination = destination,
            graphId = R.navigation.main_nav_flow,
            args = destination.args
        )
    }

    private fun requestUnitCreationForDestination(destination: FlowDestination) {
        when (destination) {
            FlowDestination.ConnectionsListFlow -> {
                _unitRequestHandler?.requestUnitCreation(unit = ConnectionsListUnit)
            }
        }
    }
}