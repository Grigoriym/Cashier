package com.grappim.products

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.grappim.common.di.FeatureNavController
import com.grappim.common.di.FeatureScope
import com.grappim.logger.logD
import com.grappim.products.model.CreateEditFlow
import com.grappim.products.root.di.ProductsScreenNavigator
import dagger.Lazy
import javax.inject.Inject

@FeatureScope
class ProductsScreenNavigatorImpl @Inject constructor(
    private val activity: AppCompatActivity,
    @FeatureNavController private val navController: Lazy<NavController>
) : ProductsScreenNavigator {

    private fun navigateTo(
        @IdRes resId: Int,
        args: Bundle? = null
    ) {
        navController
            .get()
            .navigate(
                resId = resId,
                args = args
            )
    }

    override fun goToCreateProduct() {
        navigateTo(
            resId = R.id.action_products_to_createProduct,
            args = Bundle(1).apply {
                putSerializable(BundleArgsKeys.ARG_KEY_FLOW, CreateEditFlow.CREATE)
            }
        )
    }

    override fun goToEditProduct(args: Bundle) {
        navigateTo(
            resId = R.id.action_products_to_editProduct,
            args = args
        )
    }

    override fun goToCreateCategory() {

    }

    override fun goBack() {
        logD(" $this navigator goBack | ${navController.get()}")
        navController.get().popBackStack()
    }

    override fun activityOnBackPressed() {
        activity.onBackPressed()
    }
}