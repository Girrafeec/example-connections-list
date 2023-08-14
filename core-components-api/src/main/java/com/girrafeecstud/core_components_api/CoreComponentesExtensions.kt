/* Created by Girrafeec */

package com.girrafeecstud.core_components_api

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}

fun Activity.openGPSSettings() {
    Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        .also(::startActivity)
}

fun Activity.vibrate(
    vibrationMillis: Long = DEFAULT_VIBRATION_MILLIS,
    vibrationAmplitude: Int = DEFAULT_VIBRATION_AMPLITUDE
) {
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager =
            this.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator.vibrate(VibrationEffect.createOneShot(vibrationMillis, vibrationAmplitude))
    } else {
        @Suppress("DEPRECATION")
        vibrator.vibrate(vibrationMillis)
    }
}

fun Activity.intensiveVibrate() {
    this.vibrate(vibrationAmplitude = INTENSIVE_VIBRATION_AMPLITUDE)
}

fun Fragment.vibrate(
    vibrationMillis: Long = DEFAULT_VIBRATION_MILLIS,
    vibrationAmplitude: Int = DEFAULT_VIBRATION_AMPLITUDE
) {
    requireActivity().vibrate(vibrationMillis, vibrationAmplitude)
}

fun Fragment.intensiveVibrate() {
    requireActivity().intensiveVibrate()
}

internal const val DEFAULT_VIBRATION_MILLIS = 100L

internal const val DEFAULT_VIBRATION_AMPLITUDE = VibrationEffect.DEFAULT_AMPLITUDE

internal const val INTENSIVE_VIBRATION_AMPLITUDE = VibrationEffect.EFFECT_TICK