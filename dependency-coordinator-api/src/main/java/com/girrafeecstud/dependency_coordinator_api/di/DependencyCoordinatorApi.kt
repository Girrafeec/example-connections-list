/* Created by Girrafeec */

package com.girrafeecstud.dependency_coordinator_api.di

import com.girrafeecstud.dependency_coordinator_api.IComponentCreationRequestHandler

interface DependencyCoordinatorApi {
    fun getComponentCreationRequestHandler(): IComponentCreationRequestHandler
}