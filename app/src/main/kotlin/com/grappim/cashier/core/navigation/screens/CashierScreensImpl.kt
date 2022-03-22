package com.grappim.cashier.core.navigation.screens

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.grappim.auth.ui.view.AuthFragment
import com.grappim.bag.ui.view.BagFragment
import com.grappim.common.di.ActivityScope
import com.grappim.feature.settings.ui.SettingsFragment
import com.grappim.menu.ui.view.MenuFragment
import com.grappim.navigation.screens.CashierScreens
import com.grappim.payment_method.ui.view.PaymentMethodFragment
import com.grappim.product_category.presentation.create_edit.ui.view.CreateEditProductCategoryFragment
import com.grappim.product_category.presentation.list.ui.view.ProductCategoryListFragment
import com.grappim.product_category.presentation.root.ui.ProductCategoryRootFragment
import com.grappim.products.presentation.create_edit.ui.view.CreateEditProductFragment
import com.grappim.products.presentation.list.ui.view.ProductListFragment
import com.grappim.products.presentation.root.ui.ProductsRootFragment
import com.grappim.root_presentation.ui.SelectInfoRootFragment
import com.grappim.sales.ui.SalesFragment
import com.grappim.scanner.ui.ScannerFragment
import com.grappim.sign_up_presentation.ui.view.SignUpFragment
import com.grappim.waybill.ui.details.ui.view.WaybillDetailsFragment
import com.grappim.waybill.ui.list.ui.view.WaybillListFragment
import com.grappim.waybill.ui.product.ui.view.WaybillProductFragment
import com.grappim.waybill.ui.root.ui.view.WaybillRootFragment
import com.grappim.waybill.ui.scanner.ui.WaybillScannerFragment
import com.grappim.waybill.ui.search.ui.view.SearchProductFragment
import javax.inject.Inject

@ActivityScope
class CashierScreensImpl @Inject constructor(

) : CashierScreens {

    override fun AuthScreen() = FragmentScreen {
        AuthFragment()
    }

    override fun SignUpScreen() = FragmentScreen {
        SignUpFragment()
    }

    override fun MenuScreen() = FragmentScreen {
        MenuFragment()
    }

    override fun SelectInfoRoot() = FragmentScreen {
        SelectInfoRootFragment()
    }

    override fun Products() = FragmentScreen {
        ProductsRootFragment()
    }

    override fun ProductsList() = FragmentScreen {
        ProductListFragment()
    }

    override fun ProductsCreate(args: Bundle) = FragmentScreen {
        CreateEditProductFragment.newInstance(args)
    }

    override fun ProductsEdit(args: Bundle) = FragmentScreen {
        CreateEditProductFragment.newInstance(args)
    }

    override fun ProductCategories(fromProduct: Boolean?) = FragmentScreen {
        ProductCategoryRootFragment.newInstance(fromProduct)
    }

    override fun ProductCategoriesList() = FragmentScreen {
        ProductCategoryListFragment()
    }

    override fun ProductCategoryCreate(args: Bundle?) = FragmentScreen {
        CreateEditProductCategoryFragment.newInstance(args)
    }

    override fun ProductCategoryEdit(args: Bundle) = FragmentScreen {
        CreateEditProductCategoryFragment.newInstance(args)
    }

    override fun Scanner(args: Bundle?) = FragmentScreen {
        ScannerFragment.newInstance(args)
    }

    override fun Waybill() = FragmentScreen {
        WaybillRootFragment()
    }

    override fun WaybillList() = FragmentScreen {
        WaybillListFragment()
    }

    override fun WaybillDetails() = FragmentScreen {
        WaybillDetailsFragment.newInstance()
    }

    override fun WaybillProduct(args: Bundle) = FragmentScreen {
        WaybillProductFragment.newInstance(args)
    }

    override fun WaybillSearch() = FragmentScreen {
        SearchProductFragment()
    }

    override fun WaybillScanner() = FragmentScreen {
        WaybillScannerFragment()
    }

    override fun Sales() = FragmentScreen {
        SalesFragment()
    }

    override fun Bag() = FragmentScreen {
        BagFragment()
    }

    override fun PaymentMethod() = FragmentScreen {
        PaymentMethodFragment()
    }

    override fun Settings() = FragmentScreen {
        SettingsFragment()
    }

}