package com.grappim.feature.waybill.presentation.ui.root.ui.view

import androidx.fragment.app.viewModels
import com.grappim.cashier.common.di.ComponentDependenciesProvider
import com.grappim.cashier.common.di.deps.HasComponentDeps
import com.grappim.cashier.core.base.BaseFlowFragment
import com.grappim.cashier.core.di.componentsdeps.findComponentDependencies
import com.grappim.cashier.core.di.vm.MultiViewModelFactory
import com.grappim.feature.waybill.presentation.R
import com.grappim.feature.waybill.presentation.ui.root.di.DaggerWaybillRootComponent
import com.grappim.feature.waybill.presentation.ui.root.di.WaybillRootComponent
import com.grappim.feature.waybill.presentation.ui.root.ui.viewmodel.WaybillRootViewModel
import com.grappim.navigation.router.FlowRouter

class WaybillRootFragment :
    BaseFlowFragment<WaybillRootViewModel>(R.layout.fragment_waybill_root),
    HasComponentDeps {

    private val waybillComponent: WaybillRootComponent by lazy {
        DaggerWaybillRootComponent
            .factory()
            .create(
                waybillRootDeps = findComponentDependencies(),
                fragmentManager = childFragmentManager
            )
    }

    override val flowRouter: FlowRouter by lazy {
        waybillComponent.flowRouter()
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

    override fun initialScreen() {
        flowRouter.showWaybillList()
    }
}
