/* Created by Girrafeec */

package com.girrafeecstud.navigation_api

interface INavigationDestination {
    val destinationId: Int
    val args: Map<String, Any>
}