/* Created by Girrafeec */

package com.girrafeecstud.navigation_api

interface INavigator<in Destination: INavigationDestination> {

    fun navigateToDestination(destination: Destination)

    fun setStartDestination(destination: Destination)

}