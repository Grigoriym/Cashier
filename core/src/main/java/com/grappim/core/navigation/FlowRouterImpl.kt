package com.grappim.core.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.grappim.common.di.FeatureFragmentManager
import com.grappim.common.di.FeatureNavigatorHolder
import com.grappim.common.di.FeatureRouterQualifier
import com.grappim.common.di.FeatureScope
import com.grappim.navigation.helpers.BackFragment
import com.grappim.navigation.router.ActivityRouter
import com.grappim.navigation.router.FlowRouter
import com.grappim.navigation.screens.CashierScreens
import javax.inject.Inject

@FeatureScope
class FlowRouterImpl @Inject constructor(
    private val cashierScreens: CashierScreens,
    private val activityRouter: ActivityRouter,
    @FeatureFragmentManager private val fragmentManager: FragmentManager,
    @FeatureRouterQualifier private val router: Router,
    @FeatureNavigatorHolder private val navigatorHolder: NavigatorHolder
) : FlowRouter {

    @IdRes
    private var containerId: Int = 0

    override fun setNavigator(navigator: Navigator) {
        navigatorHolder.setNavigator(navigator)
    }

    override fun removeNavigator() {
        navigatorHolder.removeNavigator()
    }

    override fun setContainerId(containerId: Int) {
        this.containerId = containerId
    }

    override fun onBackPressed() {
        val backStackCount = fragmentManager.backStackEntryCount
        if (backStackCount == 0) {
            finishFlow()
        } else {
            goBack()
        }
    }

    override fun flowOnBackPressed() {
        val fragment = fragmentManager
            .findFragmentById(containerId) as? BackFragment
        fragment?.onBackPressed() ?: onBackPressed()
    }

    override fun goBack() {
        router.exit()
    }

    override fun finishFlow() {
        activityRouter.router.exit()
    }

    override fun goToMenu() {
        activityRouter.goToMenu()
    }

    override fun goToSales() {
        activityRouter.goToSales()
    }

    override fun goToProductCategories() {
        activityRouter.goToProductCategories()
    }

    override fun goToSignUpFromSignIn() {
        activityRouter.goToSignUpFromSignIn()
    }

    override fun goToSelectInfo() {
        activityRouter.goToSelectInfo()
    }

    override fun showProductCategoriesList() {
        router.replaceScreen(cashierScreens.ProductCategoriesList())
    }

    override fun showCreateProductCategory() {
        router.replaceScreen(cashierScreens.ProductCategoryCreate(null))
    }

    override fun goToEditProductCategory(args: Bundle) {
        router.navigateTo(cashierScreens.ProductCategoryEdit(args))
    }

    override fun goToProducts() {
        activityRouter.goToProducts()
    }

    override fun goToCreateProductCategory(args: Bundle) {
        router.navigateTo(cashierScreens.ProductCategoryCreate(args))
    }

    override fun showProductsList() {
        router.replaceScreen(cashierScreens.ProductsList())
    }

    override fun goToCreateProduct(args: Bundle) {
        router.navigateTo(cashierScreens.ProductsCreate(args))
    }

    override fun goToEditProduct(args: Bundle) {
        router.navigateTo(cashierScreens.ProductsEdit(args))
    }

    override fun goToCreateCategoryFromProduct() {
        activityRouter.router.navigateTo(cashierScreens.ProductCategories(true))
    }

    override fun goToScanner() {
        activityRouter.router.navigateTo(cashierScreens.Scanner(null))
    }

    override fun goToWaybill() {
        activityRouter.goToWaybill()
    }

    override fun showWaybillList() {
        router.replaceScreen(cashierScreens.WaybillList())
    }

    override fun goToWaybillDetails() {
        router.navigateTo(cashierScreens.WaybillDetails())
    }

    override fun goToWaybillProduct(args: Bundle) {
        router.navigateTo(cashierScreens.WaybillProduct(args))
    }

    override fun goToWaybillSearch() {
        router.navigateTo(cashierScreens.WaybillSearch())
    }

    override fun goToWaybillScanner() {
        router.navigateTo(cashierScreens.WaybillScanner())
    }

    override fun returnToWaybillFromProduct() {
        router.backTo(cashierScreens.WaybillDetails())
    }

    override fun goToBag() {
        activityRouter.router.navigateTo(cashierScreens.Bag())
    }

    override fun goToPaymentMethod() {
        activityRouter.router.navigateTo(cashierScreens.PaymentMethod())
    }

    override fun returnToSalesFromPaymentMethod() {
        activityRouter.router.backTo(cashierScreens.Sales())
    }

    override fun goToSettings() {
        activityRouter.router.navigateTo(cashierScreens.Settings())
    }

    override fun goToGithubSrc() {
        activityRouter.router.navigateTo(cashierScreens.GithubSrc())
    }

}