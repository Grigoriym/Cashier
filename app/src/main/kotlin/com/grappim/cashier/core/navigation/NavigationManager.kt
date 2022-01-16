package com.grappim.cashier.core.navigation

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.grappim.auth.di.AuthScreenNavigator
import com.grappim.bag.di.BagScreenNavigator
import com.grappim.cashier.R
import com.grappim.cashier.di.splash.SplashScreenNavigator
import com.grappim.common.di.ActivityScope
import com.grappim.menu.di.MenuScreenNavigator
import com.grappim.payment_method.di.PaymentMethodScreenNavigator
import com.grappim.product_category.presentation.root.di.ProductCategoryScreenNavigator
import com.grappim.product_category.presentation.root.ui.ProductCategoryRootFragment
import com.grappim.products.root.di.ProductsScreenNavigator
import com.grappim.root_presentation.ui.SelectInfoRootFragment
import com.grappim.sales.di.SalesScreenNavigator
import com.grappim.select_info.common_navigation.SelectInfoFlowScreenNavigator
import com.grappim.sign_up.di.SignUpScreenNavigator
import com.grappim.waybill.ui.root.di.WaybillScreenNavigator
import dagger.Lazy
import javax.inject.Inject

@ActivityScope
class NavigationManager @Inject constructor(
    private val supportFragmentManager: FragmentManager,
    private val navController: Lazy<NavController>,
    private val activity: AppCompatActivity
) : AuthScreenNavigator, SignUpScreenNavigator,
    MenuScreenNavigator,
    SalesScreenNavigator,
    BagScreenNavigator,
    PaymentMethodScreenNavigator,
    WaybillScreenNavigator,
    SplashScreenNavigator,
    ProductsScreenNavigator,
    ProductCategoryScreenNavigator,
    SelectInfoFlowScreenNavigator {

    private fun navigateTo(directions: NavDirections) {
        navController
            .get()
            .navigate(directions)
    }

    private fun navigateTo(@IdRes resId: Int) {
        navController
            .get()
            .navigate(resId)
    }

    private fun navigateBack() {
        navController.get().popBackStack()
    }

    override fun goToAuthFromSplash() {
        navigateTo(R.id.action_splash_to_auth)
    }

    override fun goToSignUp() {
        navigateTo(R.id.action_authFlow_to_signUpFlow)
    }

    override fun returnToAuthFromSignUp() {
        navigateBack()
    }

    override fun goToSelectStock() {
//        supportFragmentManager.commit {
//            setReorderingAllowed(true)
//            setCustomAnimations(
//                R.anim.enter_from_right,
//                R.anim.exit_to_left,
//                R.anim.pop_enter_from_left,
//                R.anim.pop_exit_to_right
//            )
//            replace<SelectInfoRootFragment>(
//                R.id.nav_host_fragment,
//                SelectInfoRootFragment::class.java.canonicalName
//            )
//        }
        navigateTo(R.id.select_info_flow)
    }

    override fun goToSelectCashBox() {
//        navigateTo(R.id.action_selectStockFlow_to_selectCashboxFlow)
    }

    override fun goToMenu() {
        navigateTo(R.id.action_selectInfo_to_menuFlow)
    }

    override fun goToWaybill() {
        navigateTo(R.id.action_menuFlow_to_waybillFlow)
    }

    override fun goToProducts() {
        navigateTo(R.id.action_menuFlow_to_productsFlow)
    }

    override fun goToSales() {
        navigateTo(R.id.action_menuFlow_to_salesFlow)
    }

    override fun goToBag() {
        navigateTo(R.id.action_salesFlow_to_bagFlow)
    }

    override fun goToScannerFromSales() {
    }

    override fun goToPaymentMethod() {
        navigateTo(R.id.action_bagFlow_to_paymentMethodFlow)
    }

    override fun goToScannerFromBag() {

    }

    override fun fromPaymentMethodToSales() {
        navigateTo(R.id.action_paymentMethodFlow_to_salesFlow)
    }

    override fun goToWaybillList() {
    }

    override fun goToProductCategories() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<ProductCategoryRootFragment>(
                R.id.nav_host_fragment,
                ProductCategoryRootFragment::class.java.canonicalName
            )
        }
        navController.get().setGraph(
            R.navigation.product_categories_flow
        )
//        navigateTo(R.id.action_menuFlow_to_ProductCategoriesFlow)
    }

    override fun goToProductCategoryDetails() {
    }

    override fun goToCreateProductCategory() {
        navigateTo(R.id.action_productCategoryList_to_createEditProductCategory)
    }

    override fun goBack() {
        navigateBack()
    }

    override fun onBackPressed() {
        activity.onBackPressed()
    }
}