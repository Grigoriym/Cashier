package com.grappim.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.grappim.extensions.getErrorMessage
import com.grappim.extensions.showToast
import com.grappim.domain.base.Result
import com.grappim.uikit.theme.CashierTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                AuthFragmentScreen()
            }
        }
    }

    @Composable
    private fun AuthFragmentScreen() {
        val viewModel: AuthViewModel = viewModel()
        val loginStatus by viewModel.loginStatus

        com.grappim.uikit.compose.LoaderDialogCompose(
            show = loginStatus is Result.Loading,
            onClose = {}
        )
        showLoginStatus(loginStatus, viewModel)

        AuthScreen(
            onSignInClick = {
                viewModel.login(it)
            }
        )
    }

    private fun showLoginStatus(data: Result<Unit>?, viewModel: AuthViewModel) {
        when (data) {
            is Result.Success -> {
                viewModel.loginStatusDuctTape()
                val request = NavDeepLinkRequest.Builder
                    .fromUri("android-app://com.grappim.cashier.app/selectOutletFragment".toUri())
                    .build()
                findNavController().navigate(request)
            }
            is Result.Error -> {
                showToast(getErrorMessage(data.exception))
            }
        }
    }

}