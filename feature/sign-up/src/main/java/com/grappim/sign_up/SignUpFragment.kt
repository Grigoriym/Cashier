package com.grappim.sign_up

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
import com.grappim.domain.base.Try
import com.grappim.domain.model.sign_up.SignUpData
import com.grappim.domain.model.sign_up.SignUpFieldsValidationData
import com.grappim.extensions.getErrorMessage
import com.grappim.extensions.showToast
import com.grappim.uikit.compose.LoaderDialogCompose
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

        val status by viewModel.signUpStatus.observeAsState(initial = Try.Initial)
        val validationData by viewModel.signUpValidation.observeAsState(initial = null)

        LoaderDialogCompose(show = status is Try.Loading)
        LaunchedEffect(key1 = status) {
            showStatus(status)
        }
        LaunchedEffect(key1 = validationData) {
            showValidationErrors(validationData)
        }

        SignUpScreen(
            onSignUpClick = viewModel::signUp,
            phoneText = data.phone,
            setPhone = viewModel::setPhone,
            password = data.password,
            setPassword = viewModel::setPassword,
            email = data.email,
            setEmail = viewModel::setEmail
        )
    }

    private fun showValidationErrors(validationData: SignUpFieldsValidationData?) {
        if (validationData != null) {

        }
    }

    private fun showStatus(data: Try<Unit>) {
        when (data) {
            is Try.Error -> {
                showToast(getErrorMessage(data.exception))
            }
        }
    }

}