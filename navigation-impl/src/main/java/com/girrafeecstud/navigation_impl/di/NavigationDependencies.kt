package com.girrafeecstud.navigation_impl.di

import com.girrafeecstud.dependency_coordinator_api.IUnitCreationRequestHandler

interface NavigationDependencies {

    fun getUnitCreationRequestHandler(): IUnitCreationRequestHandler

}
