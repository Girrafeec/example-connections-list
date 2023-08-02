/* Created by Girrafeec */

package com.girrafeecstud.core_ui.presentation

import androidx.lifecycle.ViewModel

abstract class BaseComponentViewModel : ViewModel() {

    init {
        initComponent()
    }

    protected open fun initComponent() {}

    protected open fun destroyComponent() {}

    override fun onCleared() {
        destroyComponent()
    }
}