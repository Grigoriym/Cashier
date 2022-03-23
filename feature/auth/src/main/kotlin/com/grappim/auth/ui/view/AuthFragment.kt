package com.grappim.auth.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.auth.di.AuthComponent
import com.grappim.auth.di.DaggerAuthComponent
import com.grappim.auth.ui.viewmodel.AuthViewModel
import com.grappim.core.base.BaseFlowFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme

class AuthFragment : BaseFlowFragment<AuthViewModel>() {

    private val component: AuthComponent by lazy {
        DaggerAuthComponent
            .factory()
            .create(
                authDeps = findComponentDependencies(),
                fragmentManager = childFragmentManager
            )
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        component.viewModelFactory()
    }

    override val viewModel: AuthViewModel by viewModels {
        viewModelFactory
    }

    override val flowRouter: FlowRouter by lazy {
        component.flowRouter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        ComposeView(requireContext()).apply {
            setContent {
                CashierTheme {
                    AuthFragmentScreen()
                }
            }
        }

    @Composable
    private fun AuthFragmentScreen() {
        val loading by viewModel.loading.observeAsState(false)
        val authData by viewModel.authFieldsData.collectAsState()
        val snackbar by viewModel.showDevSnackbar.observeAsState()

        LoaderDialogCompose(
            show = loading
        )

        AuthScreen(
            onSignInClick = viewModel::login,
            onRegisterClick = viewModel::goToRegisterFlow,
            phone = authData.phone,
            setPhone = viewModel::setPhone,
            password = authData.password,
            setPassword = viewModel::setPassword,
            isPhoneFullyEntered = authData.isPhoneFullyEntered,
            onImePasswordActionDone = viewModel::loginFromIme,
            onSettingsClick = viewModel::goToSettings,
            logoCounter = viewModel::onLogoClick
        )
    }

}