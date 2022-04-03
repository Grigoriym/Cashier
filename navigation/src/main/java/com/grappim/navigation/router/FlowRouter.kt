package com.grappim.navigation.router

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Navigator

interface FlowRouter {

    fun setNavigator(navigator: Navigator)
    fun removeNavigator()
    fun setContainerId(@IdRes containerId: Int)

    fun onBackPressed()
    fun flowOnBackPressed()

    fun goBack()
    fun finishFlow()

    fun goToSignUpFromSignIn()
    fun goToSelectInfo()

    fun showProductCategoriesList()
    fun showCreateProductCategory()
    fun goToCreateProductCategory(args: Bundle)
    fun goToEditProductCategory(args: Bundle)

    fun showProductsList()
    fun goToCreateProduct(args: Bundle)
    fun goToEditProduct(args: Bundle)
    fun goToCreateCategoryFromProduct()

    fun goToScanner()

    fun showWaybillList()
    fun goToWaybillDetails()
    fun goToWaybillProduct(args: Bundle)
    fun goToWaybillSearch()
    fun goToWaybillScanner()
    fun returnToWaybillFromProduct()

    fun goToBag()
    fun goToPaymentMethod()
    fun returnToSalesFromPaymentMethod()

    fun goToSettings()
    fun goToGithubSrc()

}