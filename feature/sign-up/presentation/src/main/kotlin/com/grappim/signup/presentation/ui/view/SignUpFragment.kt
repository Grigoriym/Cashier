package com.grappim.signup.presentation.ui.view

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
import com.grappim.core.base.BaseFlowFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.navigation.router.FlowRouter
import com.grappim.signup.presentation.di.DaggerSignUpComponent
import com.grappim.signup.presentation.di.SignUpComponent
import com.grappim.signup.presentation.ui.viewmodel.SignUpViewModel
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme

class SignUpFragment : BaseFlowFragment<SignUpViewModel>() {

    private val component: SignUpComponent by lazy {
        DaggerSignUpComponent
            .factory()
            .create(
                signUpDeps = findComponentDependencies(),
                fragmentManager = childFragmentManager
            )
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        component.multiViewModelFactory()
    }

    override val flowRouter: FlowRouter by lazy {
        component.flowRouter()
    }

    override val viewModel: SignUpViewModel by viewModels {
        viewModelFactory
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
        val data by viewModel.signUpData.collectAsState()
        val validationData by viewModel.signUpValidation.collectAsState()
        val loading by viewModel.loading.observeAsState(false)

        LoaderDialogCompose(show = loading)

        SignUpScreen(
            onSignUpClick = viewModel::signUpClicked,
            phoneText = data.phone,
            setPhone = viewModel::setPhone,
            password = data.password,
            setPassword = viewModel::setPassword,
            email = data.email,
            setEmail = viewModel::setEmail,
            validationData = validationData,
            repeatPassword = data.repeatPassword,
            repeatPasswordSet = viewModel::setRepeatPassword
        )
    }
}
