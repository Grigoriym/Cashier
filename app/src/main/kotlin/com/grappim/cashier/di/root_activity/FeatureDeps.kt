package com.grappim.cashier.di.root_activity

import com.grappim.auth.di.AuthDeps
import com.grappim.bag.di.BagDeps
import com.grappim.cashbox.di.SelectCashBoxDeps
import com.grappim.menu.di.MenuDeps
import com.grappim.payment_method.di.PaymentMethodDeps
import com.grappim.sales.di.SalesDeps
import com.grappim.sign_up.di.SignUpDeps
import com.grappim.stock.di.SelectStockDeps
import com.grappim.waybill.di.WaybillRootDeps

interface FeatureDeps : AuthDeps,
    SignUpDeps,
    SelectStockDeps,
    SelectCashBoxDeps,
    MenuDeps,
    SalesDeps,
    BagDeps,
    PaymentMethodDeps,
    WaybillRootDeps