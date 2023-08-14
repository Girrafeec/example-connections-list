/* Created by Girrafeec */

package com.girrafeecstud.core_base.base

fun String.removeTrailingZeros(): String {
    val number = this.toDoubleOrNull()
    return number?.toString()?.removeSuffix(".0") ?: this
}