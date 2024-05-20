package com.grappim.cashier.core.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.grappim.cashier.common.di.FeatureFragmentManager
import com.grappim.cashier.common.di.FeatureNavigatorHolder
import com.grappim.cashier.common.di.FeatureRouterQualifier
import com.grappim.cashier.common.di.FeatureScope
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
        router.replaceScreen(cashierScreens.productCategoriesList())
    }

    override fun showCreateProductCategory() {
        router.replaceScreen(cashierScreens.productCategoryCreate(null))
    }

    override fun goToEditProductCategory(args: Bundle) {
        router.navigateTo(cashierScreens.productCategoryEdit(args))
    }

    override fun goToProducts() {
        activityRouter.goToProducts()
    }

    override fun goToCreateProductCategory(args: Bundle) {
        router.navigateTo(cashierScreens.productCategoryCreate(args))
    }

    override fun showProductsList() {
        router.replaceScreen(cashierScreens.productsList())
    }

    override fun goToCreateProduct(args: Bundle) {
        router.navigateTo(cashierScreens.productsCreate(args))
    }

    override fun goToEditProduct(args: Bundle) {
        router.navigateTo(cashierScreens.productsEdit(args))
    }

    override fun goToCreateCategoryFromProduct() {
        activityRouter.router.navigateTo(cashierScreens.productCategories(true))
    }

    override fun goToScanner() {
        activityRouter.router.navigateTo(cashierScreens.scanner(null))
    }

    override fun goToWaybill() {
        activityRouter.goToWaybill()
    }

    override fun showWaybillList() {
        router.replaceScreen(cashierScreens.waybillList())
    }

    override fun goToWaybillDetails() {
        router.navigateTo(cashierScreens.waybillDetails())
    }

    override fun goToWaybillProduct(args: Bundle) {
        router.navigateTo(cashierScreens.waybillProduct(args))
    }

    override fun goToWaybillSearch() {
        router.navigateTo(cashierScreens.waybillSearch())
    }

    override fun goToWaybillScanner() {
        router.navigateTo(cashierScreens.waybillScanner())
    }

    override fun returnToWaybillFromProduct() {
        router.backTo(cashierScreens.waybillDetails())
    }

    override fun goToBag() {
        activityRouter.router.navigateTo(cashierScreens.bag())
    }

    override fun goToPaymentMethod() {
        activityRouter.router.navigateTo(cashierScreens.paymentMethod())
    }

    override fun returnToSalesFromPaymentMethod() {
        activityRouter.router.backTo(cashierScreens.sales())
    }

    override fun goToSettings() {
        activityRouter.router.navigateTo(cashierScreens.settings())
    }

    override fun goToGithubSrc() {
        activityRouter.router.navigateTo(cashierScreens.githubSrc())
    }
}
