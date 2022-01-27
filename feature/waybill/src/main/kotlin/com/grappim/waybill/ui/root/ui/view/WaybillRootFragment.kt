package com.grappim.waybill.ui.root.ui.view

import androidx.fragment.app.viewModels
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.deps.HasComponentDeps
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.waybill.R
import com.grappim.waybill.ui.root.di.DaggerWaybillRootComponent
import com.grappim.waybill.ui.root.di.WaybillRootComponent
import com.grappim.waybill.ui.root.ui.viewmodel.WaybillRootViewModel

class WaybillRootFragment : BaseFragment<WaybillRootViewModel>(
    R.layout.fragment_waybill_root
), HasComponentDeps {

    private val waybillComponent: WaybillRootComponent by lazy {
        DaggerWaybillRootComponent
            .factory()
            .create(
                waybillRootDeps = findComponentDependencies(),
                fragmentManager = childFragmentManager
            )
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        waybillComponent.multiViewModelFactory()
    }

    override val deps: ComponentDependenciesProvider by lazy {
        waybillComponent.deps()
    }

    override val viewModel: WaybillRootViewModel by viewModels {
        viewModelFactory
    }
}