package com.grappim.core.di.components_deps.navigation

import com.grappim.di.deps.ComponentDeps
import com.grappim.navigation.directions.select_stock.SelectStockScreenNavigator

interface SelectStockNavigationDeps : ComponentDeps {

    fun selectStockScreenNavigator(): SelectStockScreenNavigator

}