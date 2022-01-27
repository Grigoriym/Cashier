package com.grappim.waybill

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.grappim.common.di.FeatureNavController
import com.grappim.common.di.FeatureScope
import com.grappim.waybill.ui.root.di.WaybillScreenNavigator
import dagger.Lazy
import javax.inject.Inject

@FeatureScope
class WaybillScreenNavigatorImpl @Inject constructor(
    private val activity: AppCompatActivity,
    @FeatureNavController private val navController: Lazy<NavController>
) : WaybillScreenNavigator {

    override fun goToWaybillDetails(args: Bundle) {
        navigateTo(
            resId = R.id.action_waybill_to_waybillDetails,
            args = args
        )
    }

    override fun goToWaybillScanner() {
        navigateTo(R.id.action_waybillDetails_to_waybillScanner)
    }

    override fun goToProductSearch() {
        navigateTo(R.id.action_waybill_to_search)
    }

    override fun goToWaybillProduct() {
        navigateTo(R.id.action_waybill_to_product)
    }

    override fun goFromDetailsToList() {
        navigateTo(R.id.action_waybillDetails_to_waybillList)
    }

    override fun goFromDetailsToScanner() {
        navigateTo(R.id.action_waybillDetails_to_waybillScanner)
    }

    override fun goBack() {
        navController.get().popBackStack()
    }

    override fun activityOnBackPressed() {
        activity.onBackPressed()
    }

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
}