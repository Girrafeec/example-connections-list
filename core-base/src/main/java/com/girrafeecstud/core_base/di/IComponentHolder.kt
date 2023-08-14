/* Created by Girrafeec */

package com.girrafeecstud.core_base.di

interface IComponentHolder<Api: IComponentApi, Dependencies: IComponentDependencies> {

    fun init(dependencies: Dependencies)

    fun get(): Api

    fun reset()

}

interface IComponentApi

interface IComponentDependencies