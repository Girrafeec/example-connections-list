/* Created by Girrafeec */

package com.girrafeecstud.core_ui.ui

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.girrafeecstud.core_ui.R

class PermissionDialog private constructor(
    private val context: Context,
    private val permissionTextProvider: PermissionTextProvider,
    private val isPermanentlyDeclined: Boolean,
    private val onOkClick: () -> Unit,
    private val onDismissClick: (() -> Unit)?,
    private val onGoToAppSettingsClick: () -> Unit,
) {

    fun show() {
        val dialogTitle = R.string.permission_required
        val dialogMessage =
            permissionTextProvider.getDescription(isPermanentlyDeclined = isPermanentlyDeclined)
        val dialogPositiveButtonText =
            when (isPermanentlyDeclined) {
                true -> R.string.grant_permission
                false -> R.string.ok
            }

        val builder = AlertDialog.Builder(context)
            .setTitle(dialogTitle)
            .setMessage(dialogMessage)
            .setPositiveButton(dialogPositiveButtonText) { dialog, _ ->
                if (isPermanentlyDeclined)
                    onGoToAppSettingsClick()
                else
                    onOkClick()
                dialog.dismiss()
            }
            .setOnDismissListener { dialog ->
                if (onDismissClick == null)
                    dialog.dismiss()
                else
                    onDismissClick.invoke()
            }

        val dialog = builder.create()
        dialog.show()
    }

    class Builder(private val context: Context) {
        private var permissionTextProvider: PermissionTextProvider? = null
        private var isPermanentlyDeclined: Boolean? = null
        private var onOkClick: (() -> Unit)? = null
        private var onDismissClick: (() -> Unit)? = null
        private var onGoToAppSettingsClick: (() -> Unit)? = null

        fun setPermissionTextProvider(permissionTextProvider: PermissionTextProvider): Builder {
            this.permissionTextProvider = permissionTextProvider
            return this
        }

        fun setIsPermanentlyDeclined(isPermanentlyDeclined: Boolean): Builder {
            this.isPermanentlyDeclined = isPermanentlyDeclined
            return this
        }

        fun setOnOkClick(onOkClick: () -> Unit): Builder {
            this.onOkClick = onOkClick
            return this
        }

        fun setOnDismissClick(onDismissClick: () -> Unit): Builder {
            this.onDismissClick = onDismissClick
            return this
        }

        fun setOnGoToAppSettingsClick(onGoToAppSettingsClick: () -> Unit): Builder {
            this.onGoToAppSettingsClick = onGoToAppSettingsClick
            return this
        }

        fun build(): PermissionDialog {
            checkNotNull(permissionTextProvider) {
                ("PermissionDialog.Builder.permissionTextProvider must be set.")
            }
            checkNotNull(isPermanentlyDeclined) {
                ("PermissionDialog.Builder.isPermanentlyDeclined must be set.")
            }
            checkNotNull(onOkClick) {
                ("PermissionDialog.Builder.onOkClick must be set.")
            }
            checkNotNull(onGoToAppSettingsClick) {
                ("PermissionDialog.Builder.onGoToAppSettingsClick must be set.")
            }

            return PermissionDialog(
                context = this.context,
                permissionTextProvider = permissionTextProvider!!,
                isPermanentlyDeclined = isPermanentlyDeclined!!,
                onOkClick = onOkClick!!,
                onDismissClick = onDismissClick,
                onGoToAppSettingsClick = onGoToAppSettingsClick!!
            )

        }
    }

}