@file:Suppress("FunctionNaming")

package com.grappim.navigation.screens

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface CashierScreens {
    fun authScreen(): FragmentScreen
    fun signUpScreen(): FragmentScreen
    fun menuScreen(): FragmentScreen
    fun selectInfoRoot(): FragmentScreen

    fun products(): FragmentScreen
    fun productsList(): FragmentScreen
    fun productsCreate(args: Bundle): FragmentScreen
    fun productsEdit(args: Bundle): FragmentScreen

    fun productCategories(fromProduct: Boolean? = false): FragmentScreen
    fun productCategoriesList(): FragmentScreen
    fun productCategoryCreate(args: Bundle?): FragmentScreen
    fun productCategoryEdit(args: Bundle): FragmentScreen

    fun scanner(args: Bundle?): FragmentScreen

    fun waybill(): FragmentScreen
    fun waybillList(): FragmentScreen
    fun waybillDetails(): FragmentScreen
    fun waybillProduct(args: Bundle): FragmentScreen
    fun waybillSearch(): FragmentScreen
    fun waybillScanner(): FragmentScreen

    fun sales(): FragmentScreen
    fun bag(): FragmentScreen
    fun paymentMethod(): FragmentScreen

    fun settings(): FragmentScreen
    fun githubSrc(): ActivityScreen
}
