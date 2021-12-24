package com.grappim.auth

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.domain.base.Try
import com.grappim.extensions.getErrorMessage
import com.grappim.extensions.showToast
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme
import di.DaggerAuthComponent

internal class AuthFragment : BaseFragment<AuthViewModel>() {

    override val viewModel: AuthViewModel by viewModels<AuthViewModel> {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    private fun performInject() {
        DaggerAuthComponent
            .builder()
            .deps(findComponentDependencies())
            .build()
            .inject(this)
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

        val showLoader = loginStatus is Try.Loading
        LoaderDialogCompose(
            show = showLoader,
            onClose = {}
        )

        LaunchedEffect(key1 = loginStatus) {
            showLoginStatus(loginStatus)
        }

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

    private fun showLoginStatus(data: Try<Unit>) {
        when (data) {
            is Try.Error -> {
                showToast(getErrorMessage(data.exception))
            }
        }
    }

}