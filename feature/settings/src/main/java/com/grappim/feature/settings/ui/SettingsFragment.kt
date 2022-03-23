package com.grappim.feature.settings.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.core.base.BaseFlowFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.feature.settings.di.DaggerSettingsComponent
import com.grappim.feature.settings.di.SettingsComponent
import com.grappim.navigation.router.FlowRouter
import com.grappim.uikit.theme.CashierTheme

class SettingsFragment : BaseFlowFragment<SettingsViewModel>() {

    private val component: SettingsComponent by lazy {
        DaggerSettingsComponent
            .factory()
            .create(
                settingsDeps = findComponentDependencies(),
                fragmentManager = childFragmentManager
            )
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        component.viewModelFactory()
    }

    override val viewModel: SettingsViewModel by viewModels {
        viewModelFactory
    }

    override val flowRouter: FlowRouter by lazy {
        component.flowRouter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        val manager = requireContext().packageManager
        val info = manager?.getPackageInfo(
            requireContext().packageName,
            0
        )
        val versionName = info?.versionName
        val versionNumber = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            info?.longVersionCode
        } else {
            info?.versionCode
        }
        val resultInfo = "$versionName $versionNumber"
        setContent {
            CashierTheme {
                SettingsFragmentScreen(resultInfo)
            }
        }
    }

    @Composable
    private fun SettingsFragmentScreen(info: String) {
        SettingsScreen(info)
    }
}