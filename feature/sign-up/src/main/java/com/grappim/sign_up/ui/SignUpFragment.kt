package com.grappim.sign_up.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findActivityComponentDependencies
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.domain.base.Try
import com.grappim.domain.model.sign_up.SignUpData
import com.grappim.domain.model.sign_up.SignUpFieldsValidationData
import com.grappim.logger.logD
import com.grappim.sign_up.di.DaggerSignUpComponent
import com.grappim.sign_up.di.SignUpComponent
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme

class SignUpFragment : BaseFragment<SignUpViewModel>() {

    override val viewModel: SignUpViewModel by viewModels {
        viewModelFactory
    }

    private var _signUpComponent: SignUpComponent? = null
    private val signUpComponent
        get() = requireNotNull(_signUpComponent)

    override fun onAttach(context: Context) {
        performInject()
        super.onAttach(context)
    }

    private fun performInject() {
        _signUpComponent = DaggerSignUpComponent
            .builder()
            .deps(findComponentDependencies())
            .navDeps(findActivityComponentDependencies())
            .build()
        signUpComponent.inject(this)
    }

    override fun onDestroy() {
        _signUpComponent = null
        super.onDestroy()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logD("${this} viewModel | viewModel = $viewModel")
    }

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
        val data by viewModel.signUpData.observeAsState(SignUpData.empty())

        val status by viewModel.signUpStatus.observeAsState(initial = Try.Initial)
        val validationData by viewModel.signUpValidation.observeAsState(initial = null)

        LoaderDialogCompose(show = status is Try.Loading)
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

}