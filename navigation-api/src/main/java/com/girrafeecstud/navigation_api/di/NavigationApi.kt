/* Created by Girrafeec */

package com.girrafeecstud.navigation_api.di

import com.girrafeecstud.navigation_api.IConnectionsListExternalScreensNavigator
import com.girrafeecstud.navigation_api.IExternalScreensNavigator
import com.girrafeecstud.navigation_api.INavigationDestination
import com.girrafeecstud.navigation_api.INavigator

interface NavigationApi {

    fun getConnectionsListExternalScreensNavigator(): IConnectionsListExternalScreensNavigator

}