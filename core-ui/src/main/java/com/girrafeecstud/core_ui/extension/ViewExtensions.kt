/* Created by Girrafeec */

package com.girrafeecstud.core_ui.extension

import android.view.View
import android.widget.ImageView
import com.girrafeecstud.core_ui.R
import com.squareup.picasso.Picasso

//fun ImageView.loadAndSetImage(
//    url: String,
//    loadingResourceId: Int,
//    errorResourceId: Int,
//) {
//    Picasso.get()
//        .load(url)
//        .placeholder(loadingResourceId)
//        .error(errorResourceId)
//        .into(this)
//}

fun ImageView.loadAndSetImage(
    url: String
) {
    Picasso.get()
        .load(url)
        .into(this)
}

fun View.hideView() {
    this.visibility = View.INVISIBLE
}

fun View.removeView() {
    this.visibility = View.GONE
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.enable() {
    this.isEnabled = true
    this.isClickable = true
}

fun View.disable() {
    this.isEnabled = false
    this.isClickable = false
}

fun View.setElevation(elevation: Float = DEFAULT_ELEVATION) {
    this.elevation = elevation
}

fun View.resetElevation() {
    this.elevation = 0.0f
}

private val DEFAULT_ELEVATION = 2.0f