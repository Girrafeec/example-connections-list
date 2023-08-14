/* Created by Girrafeec */

package com.girrafeecstud.example_connections_list.connections_list.ui

import com.girrafeecstud.core_ui.ui.PermissionTextProvider

class LocationPermissionTextProvider : PermissionTextProvider {

    override fun getDescription(isPermanentlyDeclined: Boolean): String =
        when(isPermanentlyDeclined) {
            true -> {
                "It seems you permanently declined location permission. " +
                        "You can go to the app settings to grant it."
            }
            false -> {
                "This app needs access to your location so that you can " +
                        "see the distance to your connections."
            }
        }
}