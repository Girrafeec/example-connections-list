/* Created by Girrafeec */

package com.girrafeecstud.dependency_coordinator_api.di

import com.girrafeecstud.dependency_coordinator_api.IUnitCreationRequestHandler

interface DependencyCoordinatorApi {
    fun getUnitCreationRequestHandler(): IUnitCreationRequestHandler
}