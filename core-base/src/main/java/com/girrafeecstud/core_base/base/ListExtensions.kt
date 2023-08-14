/* Created by Girrafeec */

package com.girrafeecstud.core_base.base

fun <T> List<T>.removeAt(indexToRemove: Int): List<T> {
    val mutableList = this.toMutableList()
    mutableList.removeAt(indexToRemove)
    return mutableList.toList()
}