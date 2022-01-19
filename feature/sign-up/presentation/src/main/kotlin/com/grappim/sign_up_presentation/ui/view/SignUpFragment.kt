package com.grappim.sign_up_presentation.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.common.lce.Try
import com.grappim.core.BaseFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.sign_up.domain.model.SignUpData
import com.grappim.logger.logD
import com.grappim.sign_up_presentation.di.DaggerSignUpComponent
import com.grappim.sign_up_presentation.ui.viewmodel.SignUpViewModel
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme

class SignUpFragment : BaseFragment<SignUpViewModel>() {

    private val signUpComponent by lazy {
        DaggerSignUpComponent
            .builder()
            .signUpDeps(findComponentDependencies())
            .build()
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        signUpComponent.multiViewModelFactory()
    }

    override val viewModel: SignUpViewModel by viewModels {
        viewModelFactory
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

        SignUpScreen(
            onSignUpClick = viewModel::signUpClicked,
            phoneText = data.phone,
            setPhone = viewModel::setPhone,
            password = data.password,
            setPassword = viewModel::setPassword,
            email = data.email,
            setEmail = viewModel::setEmail,
            validationData = validationData
        )
    }

}