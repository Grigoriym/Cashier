package com.grappim.auth.ui

import android.content.Context
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
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.domain.base.Try
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme

internal class AuthFragment : BaseFragment<AuthViewModel>() {

    override val viewModel: AuthViewModel by viewModels {
        viewModelFactory
    }

    private var _authComponent: AuthComponent? = null
    private val authComponent
        get() = requireNotNull(_authComponent)

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    private fun performInject() {
        _authComponent = DaggerAuthComponent
            .builder()
            .authDeps(findComponentDependencies())
            .build()
        authComponent.inject(this)
    }

    override fun onDestroy() {
        _authComponent = null
        super.onDestroy()
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
        val loginStatus by viewModel.loginStatus.observeAsState(Try.Initial)
        val authData by viewModel.authFieldsData.collectAsState()

        LoaderDialogCompose(
            show = loginStatus is Try.Loading,
            onClose = {}
        )

        AuthScreen(
            onSignInClick = viewModel::login,
            onRegisterClick = viewModel::goToRegisterFlow,
            phone = authData.phone,
            setPhone = viewModel::setPhone,
            password = authData.password,
            setPassword = viewModel::setPassword,
            isPhoneFullyEntered = authData.isPhoneFullyEntered,
            onImePasswordActionDone = viewModel::loginFromIme
        )
    }

}