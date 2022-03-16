package com.grappim.cashier.di.root_activity

import com.grappim.auth.di.AuthDeps
import com.grappim.bag.di.BagDeps
import com.grappim.menu.di.MenuDeps
import com.grappim.payment_method.di.PaymentMethodDeps
import com.grappim.product_category.presentation.root.di.ProductCategoryRootDeps
import com.grappim.products.presentation.root.di.ProductsRootDeps
import com.grappim.root_presentation.di.SelectInfoRootDeps
import com.grappim.sales.di.SalesDeps
import com.grappim.scanner.di.ScannerDeps
import com.grappim.sign_up_presentation.di.SignUpDeps
import com.grappim.waybill.ui.root.di.WaybillRootDeps

interface FeatureDeps : AuthDeps,
    SignUpDeps,
    SelectInfoRootDeps,
    MenuDeps,
    SalesDeps,
    BagDeps,
    PaymentMethodDeps,
    WaybillRootDeps,
    com.grappim.products.presentation.root.di.ProductsRootDeps,
    ProductCategoryRootDeps,
    ScannerDeps