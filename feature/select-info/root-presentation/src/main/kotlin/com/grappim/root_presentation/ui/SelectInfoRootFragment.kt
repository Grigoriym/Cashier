package com.grappim.root_presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.deps.HasComponentDeps
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.logger.logD
import com.grappim.root_presentation.R
import com.grappim.root_presentation.di.DaggerSelectInfoRootComponent
import com.grappim.root_presentation.di.SelectInfoRootComponent
import com.grappim.select_info.common_navigation.SelectInfoRootFlow
import com.grappim.select_info.common_navigation.SelectInfoViewModel
import javax.inject.Inject

class SelectInfoRootFragment : BaseFragment<SelectInfoViewModel>(
    R.layout.fragment_select_info_root
), HasComponentDeps {

    @Inject
    override lateinit var deps: ComponentDependenciesProvider
        internal set

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    override val viewModel: SelectInfoViewModel by viewModels {
        viewModelFactory
    }

    private var _rootComponent: SelectInfoRootComponent? = null
    private val rootComponent
        get() = requireNotNull(_rootComponent)

    private var _pagerAdapter: SelectInfoRootPagerAdapter? = null
    private val pagerAdapter: SelectInfoRootPagerAdapter
        get() = requireNotNull(_pagerAdapter)

    private lateinit var viewPager: ViewPager2

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        _rootComponent = null
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPagerAdapter()
        initOnBackPressedDispatcher()
        observeViewModel()
    }

    private fun initOnBackPressedDispatcher() {
        requireActivity().onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    logD("$this onBackPressed")
                    onBackPressed()
                }
            })
    }

    private fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            viewModel.goBack()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    private fun observeViewModel() {
        viewModel.nextScreen.observe(viewLifecycleOwner, ::handleNextScreen)
    }

    private fun handleNextScreen(flow: SelectInfoRootFlow) {
        logD("$this viewPager $flow ,${flow.ordinal}, ${viewPager.currentItem}")
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

    private fun performInject() {
        _rootComponent = DaggerSelectInfoRootComponent
            .factory()
            .create(findComponentDependencies())
        rootComponent.inject(this)
    }
}