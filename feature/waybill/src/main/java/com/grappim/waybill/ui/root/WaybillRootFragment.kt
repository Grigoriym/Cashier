package com.grappim.waybill.ui.root

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.di.ComponentDependenciesProvider
import com.grappim.di.deps.HasComponentDeps
import com.grappim.waybill.R
import com.grappim.waybill.di.DaggerWaybillRootComponent
import com.grappim.waybill.di.WaybillRootComponent
import javax.inject.Inject

class WaybillRootFragment : BaseFragment<WaybillRootViewModel>(
    R.layout.fragment_waybill_root
), HasComponentDeps {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}