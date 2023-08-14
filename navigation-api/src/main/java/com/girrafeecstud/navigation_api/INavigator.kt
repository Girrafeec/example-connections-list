/* Created by Girrafeec */

package com.girrafeecstud.navigation_api

interface INavigator<in INavigationDestination> {

    fun navigateToDestination(destination: INavigationDestination)

    fun setStartDestination(destination: INavigationDestination)

}