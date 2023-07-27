package com.grappim.feature.select_info.root_presentation.ui

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.grappim.cashbox.ui.view.SelectCashBoxFragment
import com.grappim.logger.logD
import com.grappim.feature.select_info.common_navigation.SelectInfoRootFlow
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
