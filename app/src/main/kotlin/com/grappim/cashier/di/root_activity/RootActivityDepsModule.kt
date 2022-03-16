package com.grappim.cashier.di.root_activity

import com.grappim.auth.di.AuthDeps
import com.grappim.bag.di.BagDeps
import com.grappim.common.di.deps.ComponentDeps
import com.grappim.common.di.deps.ComponentDepsKey
import com.grappim.menu.di.MenuDeps
import com.grappim.payment_method.di.PaymentMethodDeps
import com.grappim.product_category.presentation.root.di.ProductCategoryRootDeps
import com.grappim.products.root.di.ProductsRootDeps
import com.grappim.root_presentation.di.SelectInfoRootDeps
import com.grappim.sales.di.SalesDeps
import com.grappim.scanner.di.ScannerDeps
import com.grappim.sign_up_presentation.di.SignUpDeps
import com.grappim.waybill.ui.root.di.WaybillRootDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RootActivityDepsModule {

    @[Binds IntoMap ComponentDepsKey(AuthDeps::class)]
    fun bindAuthDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SignUpDeps::class)]
    fun bindSignUpDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SelectInfoRootDeps::class)]
    fun bindSelectInfoRootDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(MenuDeps::class)]
    fun bindMenuDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SalesDeps::class)]
    fun bindSalesDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(BagDeps::class)]
    fun bindBagDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(PaymentMethodDeps::class)]
    fun bindPaymentMethodDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(WaybillRootDeps::class)]
    fun bindWaybillRootDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(ProductsRootDeps::class)]
    fun bindProductsRootDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(ProductCategoryRootDeps::class)]
    fun bindProductCategoryRootDeps(
        component: RootActivityComponent
    ): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(ScannerDeps::class)]
    fun bindScannerDeps(
        component: RootActivityComponent
    ): ComponentDeps
}