package com.grappim.cashier.di.root_activity

import com.grappim.auth.di.AuthDeps
import com.grappim.cashbox.di.SelectCashBoxDeps
import com.grappim.menu.di.MenuDeps
import com.grappim.sign_up.di.SignUpDeps
import com.grappim.stock.di.SelectStockDeps

interface FeatureDeps : AuthDeps,
    SignUpDeps,
    SelectStockDeps,
    SelectCashBoxDeps,
    MenuDeps