package com.grappim.menu.di

import com.grappim.common.di.deps.ComponentDeps
import com.grappim.domain.storage.GeneralStorage

interface MenuDeps : ComponentDeps {

    fun generalStorage(): GeneralStorage
    fun menuScreenNavigator(): MenuScreenNavigator

}