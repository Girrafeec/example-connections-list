/* Created by Girrafeec */

package com.girrafeecstud.core_ui.ui

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.girrafeecstud.core_ui.R

class ActionDialog private constructor(
    private val context: Context,
    private val dialogTitle: String,
    private val dialogMessage: String,
    private val onPositiveClick: () -> Unit,
    private val onNegativeClick: (() -> Unit)?,
    private val onDismissClick: (() -> Unit)?,
) {

    fun show() {

        val builder = AlertDialog.Builder(context)
            .setTitle(dialogTitle)
            .setMessage(dialogMessage)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                onPositiveClick()
                dialog.dismiss()
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                if (onNegativeClick == null)
                    dialog.dismiss()
                else
                    onNegativeClick.invoke()
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
        private var title: String? = null
        private var message: String? = null
        private var onPositiveClick: (() -> Unit)? = null
        private var onNegativeClick: (() -> Unit)? = null
        private var onDismissClick: (() -> Unit)? = null

        fun setTitle(title: String): Builder {
            this.title = title
            return this
        }

        fun setMessage(message: String): Builder {
            this.message = message
            return this
        }

        fun setOnPositiveClick(onPositiveClick: () -> Unit): Builder {
            this.onPositiveClick = onPositiveClick
            return this
        }

        fun setOnNegativeClick(onNegativeClick: () -> Unit): Builder {
            this.onNegativeClick = onNegativeClick
            return this
        }

        fun setOnDismissClick(onDismissClick: () -> Unit): Builder {
            this.onDismissClick = onDismissClick
            return this
        }

        fun build(): ActionDialog {
            checkNotNull(title) {
                ("ActionDialog.Builder.title must be set.")
            }
            checkNotNull(message) {
                ("ActionDialog.Builder.message must be set.")
            }
            checkNotNull(onPositiveClick) {
                ("ActionDialog.Builder.onPositiveClick must be set.")
            }

            return ActionDialog(
                context = this.context,
                dialogTitle = title!!,
                dialogMessage = message!!,
                onPositiveClick = onPositiveClick!!,
                onNegativeClick = onNegativeClick,
                onDismissClick = onDismissClick,
            )

        }
    }

}