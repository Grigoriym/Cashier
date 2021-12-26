package com.grappim.core.di.components_deps.navigation

import com.grappim.di.deps.ComponentDeps
import com.grappim.navigation.directions.select_cashier.SelectCashBoxNavigator

interface SelectCashBoxNavigationDeps : ComponentDeps {

    fun selectCashBoxNavigationDeps(): SelectCashBoxNavigator

}