package com.grappim.product_category.presentation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.grappim.common.di.FeatureNavController
import com.grappim.common.di.FeatureScope
import com.grappim.product_category.presentation.root.di.ProductCategoryScreenNavigator
import dagger.Lazy
import javax.inject.Inject

@FeatureScope
class ProductCategoryScreenNavigatorImpl @Inject constructor(
    private val activity: AppCompatActivity,
    @FeatureNavController private val navController: Lazy<NavController>
) : ProductCategoryScreenNavigator {

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

    override fun goToEditProductCategory(args: Bundle) {
        navigateTo(
            resId = R.id.action_productCategoryList_to_editProductCategory,
            args = args
        )
    }

    override fun goToCreateProductCategory() {
        navigateTo(R.id.action_productCategoryList_to_createProductCategory)
    }

    override fun goBack() {
        navController.get().popBackStack()
    }

    override fun onBackPressed() {
        activity.onBackPressed()
    }

}