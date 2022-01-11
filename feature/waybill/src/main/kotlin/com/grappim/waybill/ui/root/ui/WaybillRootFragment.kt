package com.grappim.waybill.ui.root.ui

import android.content.Context
import androidx.fragment.app.viewModels
import com.grappim.common.di.ComponentDependenciesProvider
import com.grappim.common.di.deps.HasComponentDeps
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.waybill.R
import com.grappim.waybill.ui.root.di.DaggerWaybillRootComponent
import com.grappim.waybill.ui.root.di.WaybillRootComponent
import javax.inject.Inject

class WaybillRootFragment : BaseFragment<WaybillRootViewModel>(
    R.layout.fragment_waybill_root
), HasComponentDeps {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    @Inject
    override lateinit var deps: ComponentDependenciesProvider
        internal set

    override val viewModel: WaybillRootViewModel by viewModels {
        viewModelFactory
    }

    private var _waybillRootRootComponent: WaybillRootComponent? = null
    val waybillComponent
        get() = requireNotNull(_waybillRootRootComponent)

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    override fun onDestroy() {
        _waybillRootRootComponent = null
        super.onDestroy()
    }

    private fun performInject() {
        _waybillRootRootComponent = DaggerWaybillRootComponent
            .factory()
            .create(
                waybillRootDeps = findComponentDependencies()
            )
        waybillComponent.inject(this)
    }
}