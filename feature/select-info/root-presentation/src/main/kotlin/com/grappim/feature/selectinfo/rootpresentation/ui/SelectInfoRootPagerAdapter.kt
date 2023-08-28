package com.grappim.feature.selectinfo.rootpresentation.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.grappim.cashbox.ui.view.SelectCashBoxFragment
import com.grappim.logger.logD
import com.grappim.feature.selectinfo.commonnavigation.SelectInfoRootFlow
import com.grappim.stock.ui.view.SelectStockFragment

class SelectInfoRootPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = SelectInfoRootFlow.getAdapterSize()

    override fun createFragment(position: Int): Fragment {
        logD("$this viewPager $position")
        return when (position) {
            SelectInfoRootFlow.SELECT_STOCK.ordinal -> SelectStockFragment()
            SelectInfoRootFlow.SELECT_CASH_BOX.ordinal -> SelectCashBoxFragment()
            else -> error("Incorrect position: $position for viewPagerAdapter")
        }
    }
}
