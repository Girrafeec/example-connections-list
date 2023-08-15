/* Created by Girrafeec */

package com.girrafeecstud.core_components_api.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.girrafeecstud.core_components_api.DispatcherProvider

interface CoreComponentsApi {

    fun getApplicationContext(): Context

    fun getDispatcherProvider(): DispatcherProvider

}