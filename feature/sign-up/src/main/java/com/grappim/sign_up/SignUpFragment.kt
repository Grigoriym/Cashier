package com.grappim.sign_up

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
import com.grappim.uikit.theme.CashierTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                SignUpFragmentScreen()
            }
        }
    }

    @Composable
    private fun SignUpFragmentScreen() {
        val viewModel: SignUpViewModel = viewModel()
        val data by viewModel.signUpData.observeAsState(SignUpData.empty())

        SignUpScreen(
            onSignUpClick = {
                viewModel.signUp()
            },
            phoneText = data.phone,
            setPhone = {
                viewModel.setPhone(it)
            },
            password = data.password,
            setPassword = {
                viewModel.setPassword(it)
            },
            email = data.email,
            setEmail = {
                viewModel.setEmail(it)
            }
        )
    }

}