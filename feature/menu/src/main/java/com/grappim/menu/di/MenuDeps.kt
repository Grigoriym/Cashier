package com.grappim.menu.di

import com.grappim.di.deps.ComponentDeps
import com.grappim.domain.storage.GeneralStorage
import com.grappim.navigation.directions.menu.MenuScreenNavigator

interface MenuDeps : ComponentDeps {

    fun generalStorage(): GeneralStorage
    fun menuScreenNavigator(): MenuScreenNavigator

}