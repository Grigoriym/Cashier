package com.grappim.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grappim.domain.base.Result
import com.grappim.extensions.getErrorMessage
import com.grappim.extensions.showToast
import com.grappim.navigation.NavigationFlow
import com.grappim.navigation.Navigator
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
internal class AuthFragment : Fragment() {

    @Inject
    lateinit var navigator: Navigator

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
        val viewModel: AuthViewModel = viewModel()
        val loginStatus by viewModel.loginStatus.observeAsState(Result.Initial)

        val showLoader = loginStatus is Result.Loading
        LoaderDialogCompose(
            show = showLoader,
            onClose = {}
        )

        LaunchedEffect(key1 = loginStatus) {
            showLoginStatus(loginStatus)
        }

        AuthScreen(
            onSignInClick = {
                viewModel.login(it)
            },
            onRegisterClick = {
                viewModel.goToRegisterFlow()
            }
        )
    }

    private fun showLoginStatus(data: Result<Unit>?) {
        when (data) {
            is Result.Success -> {
                navigator.navigateToFlow(NavigationFlow.SelectInfoStockFlow)
            }
            is Result.Error -> {
                showToast(getErrorMessage(data.exception))
            }
        }
    }

}