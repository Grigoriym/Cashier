package com.grappim.feature.selectinfo.rootpresentation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.deps.HasComponentDeps
import com.grappim.core.base.BaseFlowFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.feature.select_info.root_presentation.R
import com.grappim.feature.selectinfo.commonnavigation.SelectInfoRootFlow
import com.grappim.feature.selectinfo.commonnavigation.SelectInfoViewModel
import com.grappim.feature.selectinfo.rootpresentation.di.DaggerSelectInfoRootComponent
import com.grappim.feature.selectinfo.rootpresentation.di.SelectInfoRootComponent
import com.grappim.navigation.router.FlowRouter

class SelectInfoRootFragment : BaseFlowFragment<SelectInfoViewModel>(
    R.layout.fragment_select_info_root
), HasComponentDeps {

    private val component: SelectInfoRootComponent by lazy {
        DaggerSelectInfoRootComponent
            .factory()
            .create(
                selectInfoRootDeps = findComponentDependencies(),
                fragmentManager = childFragmentManager
            )
    }

    override val deps: ComponentDependenciesProvider by lazy {
        component.deps()
    }

    override val flowRouter: FlowRouter by lazy {
        component.flowRouter()
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        component.multiViewModelFactory()
    }

    override val viewModel: SelectInfoViewModel by viewModels {
        viewModelFactory
    }

    private var _pagerAdapter: SelectInfoRootPagerAdapter? = null
    private val pagerAdapter: SelectInfoRootPagerAdapter
        get() = requireNotNull(_pagerAdapter)

    private lateinit var viewPager: ViewPager2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPagerAdapter()
        initOnBackPressedDispatcher()
        observeViewModel()
    }

    private fun initOnBackPressedDispatcher() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPressed()
                }
            })
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            viewModel.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    private fun observeViewModel() {
        viewModel.nextScreen.observe(viewLifecycleOwner, ::handleNextScreen)
    }

    private fun handleNextScreen(flow: SelectInfoRootFlow) {
        viewPager.currentItem = flow.ordinal
    }

    private fun initPagerAdapter() {
        _pagerAdapter = SelectInfoRootPagerAdapter(this)
        viewPager = requireView().findViewById<ViewPager2>(R.id.pagerSelectInfo)
        viewPager.run {
            adapter = pagerAdapter
            isUserInputEnabled = false
        }
    }
}
