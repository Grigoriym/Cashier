package com.grappim.navigation.screens

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface CashierScreens {
    fun AuthScreen(): FragmentScreen
    fun SignUpScreen(): FragmentScreen
    fun MenuScreen(): FragmentScreen
    fun SelectInfoRoot(): FragmentScreen

    fun Products(): FragmentScreen
    fun ProductsList(): FragmentScreen
    fun ProductsCreate(args: Bundle): FragmentScreen
    fun ProductsEdit(args: Bundle): FragmentScreen

    fun ProductCategories(fromProduct: Boolean? = false): FragmentScreen
    fun ProductCategoriesList(): FragmentScreen
    fun ProductCategoryCreate(args: Bundle?): FragmentScreen
    fun ProductCategoryEdit(args: Bundle): FragmentScreen

    fun Scanner(args: Bundle?): FragmentScreen

    fun Waybill(): FragmentScreen
    fun WaybillList(): FragmentScreen
    fun WaybillDetails(): FragmentScreen
    fun WaybillProduct(args: Bundle): FragmentScreen
    fun WaybillSearch(): FragmentScreen
    fun WaybillScanner(): FragmentScreen

    fun Sales(): FragmentScreen
    fun Bag(): FragmentScreen
    fun PaymentMethod(): FragmentScreen

    fun Settings(): FragmentScreen
}