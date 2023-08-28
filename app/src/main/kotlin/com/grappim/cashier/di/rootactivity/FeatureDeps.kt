package com.grappim.cashier.di.rootactivity

import com.grappim.feature.auth.presentation.di.AuthDeps
import com.grappim.feature.bag.presentation.di.BagDeps
import com.grappim.feature.selectinfo.rootpresentation.di.SelectInfoRootDeps
import com.grappim.feature.settings.di.SettingsDeps
import com.grappim.feature.waybill.presentation.ui.root.di.WaybillRootDeps
import com.grappim.menu.di.MenuDeps
import com.grappim.paymentmethod.di.PaymentMethodDeps
import com.grappim.productcategory.presentation.root.di.ProductCategoryRootDeps
import com.grappim.products.presentation.root.di.ProductsRootDeps
import com.grappim.sales.di.SalesDeps
import com.grappim.scanner.di.ScannerDeps
import com.grappim.signup.presentation.di.SignUpDeps

interface FeatureDeps : AuthDeps,
    SignUpDeps,
    SelectInfoRootDeps,
    MenuDeps,
    SalesDeps,
    BagDeps,
    PaymentMethodDeps,
    WaybillRootDeps,
    ProductsRootDeps,
    ProductCategoryRootDeps,
    ScannerDeps,
    SettingsDeps
