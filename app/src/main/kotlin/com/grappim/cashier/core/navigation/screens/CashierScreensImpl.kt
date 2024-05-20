package com.grappim.cashier.core.navigation.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.grappim.cashier.common.di.ActivityScope
import com.grappim.cashier.feature.paymentmethod.presentation.ui.view.PaymentMethodFragment
import com.grappim.cashier.feature.productcategory.presentation.createedit.ui.view.CreateEditProductCategoryFragment
import com.grappim.cashier.feature.productcategory.presentation.list.ui.view.ProductCategoryListFragment
import com.grappim.cashier.feature.productcategory.presentation.root.ui.ProductCategoryRootFragment
import com.grappim.feature.auth.presentation.ui.view.AuthFragment
import com.grappim.feature.bag.presentation.ui.view.BagFragment
import com.grappim.feature.selectinfo.rootpresentation.ui.SelectInfoRootFragment
import com.grappim.feature.settings.ui.SettingsFragment
import com.grappim.feature.waybill.presentation.ui.details.ui.view.WaybillDetailsFragment
import com.grappim.feature.waybill.presentation.ui.list.ui.view.WaybillListFragment
import com.grappim.feature.waybill.presentation.ui.product.ui.view.WaybillProductFragment
import com.grappim.feature.waybill.presentation.ui.root.ui.view.WaybillRootFragment
import com.grappim.feature.waybill.presentation.ui.scanner.ui.WaybillScannerFragment
import com.grappim.feature.waybill.presentation.ui.search.ui.view.SearchProductFragment
import com.grappim.menu.ui.view.MenuFragment
import com.grappim.navigation.screens.CashierScreens
import com.grappim.products.presentation.createedit.ui.view.CreateEditProductFragment
import com.grappim.products.presentation.list.ui.view.ProductListFragment
import com.grappim.products.presentation.root.ui.ProductsRootFragment
import com.grappim.sales.ui.SalesFragment
import com.grappim.scanner.ui.ScannerFragment
import com.grappim.signup.presentation.ui.view.SignUpFragment
import javax.inject.Inject

@ActivityScope
class CashierScreensImpl @Inject constructor() : CashierScreens {

    override fun authScreen() = FragmentScreen {
        AuthFragment()
    }

    override fun signUpScreen() = FragmentScreen {
        SignUpFragment()
    }

    override fun menuScreen() = FragmentScreen {
        MenuFragment()
    }

    override fun selectInfoRoot() = FragmentScreen {
        SelectInfoRootFragment()
    }

    override fun products() = FragmentScreen {
        ProductsRootFragment()
    }

    override fun productsList() = FragmentScreen {
        ProductListFragment()
    }

    override fun productsCreate(args: Bundle) = FragmentScreen {
        CreateEditProductFragment.newInstance(args)
    }

    override fun productsEdit(args: Bundle) = FragmentScreen {
        CreateEditProductFragment.newInstance(args)
    }

    override fun productCategories(fromProduct: Boolean?) = FragmentScreen {
        ProductCategoryRootFragment.newInstance(fromProduct)
    }

    override fun productCategoriesList() = FragmentScreen {
        ProductCategoryListFragment()
    }

    override fun productCategoryCreate(args: Bundle?) = FragmentScreen {
        CreateEditProductCategoryFragment.newInstance(args)
    }

    override fun productCategoryEdit(args: Bundle) = FragmentScreen {
        CreateEditProductCategoryFragment.newInstance(args)
    }

    override fun scanner(args: Bundle?) = FragmentScreen {
        ScannerFragment.newInstance(args)
    }

    override fun waybill() = FragmentScreen {
        WaybillRootFragment()
    }

    override fun waybillList() = FragmentScreen {
        WaybillListFragment()
    }

    override fun waybillDetails() = FragmentScreen {
        WaybillDetailsFragment.newInstance()
    }

    override fun waybillProduct(args: Bundle) = FragmentScreen {
        WaybillProductFragment.newInstance(args)
    }

    override fun waybillSearch() = FragmentScreen {
        SearchProductFragment()
    }

    override fun waybillScanner() = FragmentScreen {
        WaybillScannerFragment()
    }

    override fun sales() = FragmentScreen {
        SalesFragment()
    }

    override fun bag() = FragmentScreen {
        BagFragment()
    }

    override fun paymentMethod() = FragmentScreen {
        PaymentMethodFragment()
    }

    override fun settings() = FragmentScreen {
        SettingsFragment()
    }

    override fun githubSrc() = ActivityScreen {
        Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://github.com/Grigoriym/Cashier")
        )
    }
}
