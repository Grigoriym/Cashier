package com.grappim.cashier.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.getErrorMessage
import com.grappim.cashier.core.extensions.showToast
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.view.CashierLoaderDialog
import com.grappim.cashier.ui.theme.CashierTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthFragment : Fragment() {

    private val loader: CashierLoaderDialog by lazy {
        CashierLoaderDialog(requireContext())
    }

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
        val loginStatus by viewModel.loginStatus.observeAsState()
        showLoginStatus(loginStatus)

        AuthScreen(
            onSignInClick = {
                viewModel.login(it)
            }
        )
    }

    private fun showLoginStatus(data: Resource<Unit>?) {
        loader.showOrHide(data is Resource.Loading)
        when (data) {
            is Resource.Success -> {
                findNavController().navigate(R.id.action_authFragment_to_selectOutletFragment)
            }
            is Resource.Error -> {
                showToast(getErrorMessage(data.exception))
            }
        }
    }

}