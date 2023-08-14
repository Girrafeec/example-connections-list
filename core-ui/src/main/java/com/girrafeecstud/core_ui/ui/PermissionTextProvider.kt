package com.girrafeecstud.core_ui.ui

interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): String
}
