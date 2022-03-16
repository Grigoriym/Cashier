package com.grappim.core.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Router
import com.grappim.common.di.FeatureScope
import com.grappim.core.base.BaseFragment2
import com.grappim.navigation.AppRouter
import com.grappim.navigation.CashierScreens
import com.grappim.navigation.FlowRouter
import javax.inject.Inject

@FeatureScope
class FlowRouterImpl @Inject constructor(
    private val cashierScreens: CashierScreens,
    private val appRouter: AppRouter
) : FlowRouter {

    private lateinit var fragmentManager: FragmentManager

    @IdRes
    private var containerId: Int = 0

    private val currentFragment
        get() = fragmentManager.findFragmentById(containerId) as? BaseFragment2<*>

    private val cicerone by lazy {
        Cicerone.create()
    }

    private val router: Router by lazy {
        cicerone.router
    }

    private val navigatorHolder by lazy {
        cicerone.getNavigatorHolder()
    }

    override fun setFragmentManager(fragmentManager: FragmentManager) {
        this.fragmentManager = fragmentManager
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

    override fun goBack() {
        router.exit()
    }

    override fun finishFlow() {
        appRouter.router.exit()
    }

    override fun goToSignUpFromSignIn() {
        appRouter.router.navigateTo(cashierScreens.SignUpScreen())
    }

    override fun goToSelectInfo() {
        appRouter.router.navigateTo(cashierScreens.SelectInfoRoot())
    }

    override fun setNavigator(navigator: Navigator) {
        navigatorHolder.setNavigator(navigator)
    }

    override fun removeNavigator() {
        navigatorHolder.removeNavigator()
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
        appRouter.router.navigateTo(cashierScreens.ProductCategories(true))
    }

    override fun goToScanner() {
        appRouter.router.navigateTo(cashierScreens.Scanner(null))
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
        appRouter.router.navigateTo(cashierScreens.Bag())
    }

    override fun goToPaymentMethod() {
        appRouter.router.navigateTo(cashierScreens.PaymentMethod())
    }

    override fun returnToSalesFromPaymentMethod() {
        appRouter.router.backTo(cashierScreens.Sales())
    }

}