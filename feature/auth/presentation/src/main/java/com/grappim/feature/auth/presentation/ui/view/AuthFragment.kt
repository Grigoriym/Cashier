package com.grappim.feature.auth.presentation.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.viewModels
import com.grappim.core.base.BaseFlowFragment
import com.grappim.core.di.components_deps.findComponentDependencies
import com.grappim.core.di.vm.MultiViewModelFactory
import com.grappim.domain.model.biometrics.BiometricsStatus
import com.grappim.feature.auth.presentation.di.AuthComponent
import com.grappim.feature.auth.presentation.di.DaggerAuthComponent
import com.grappim.feature.auth.presentation.model.BiometricsDialogClickState
import com.grappim.feature.auth.presentation.model.BiometricsState
import com.grappim.feature.auth.presentation.ui.viewmodel.AuthViewModel
import com.grappim.logger.logD
import com.grappim.navigation.router.FlowRouter
import com.grappim.uikit.compose.LoaderDialogCompose
import com.grappim.uikit.theme.CashierTheme

class AuthFragment : BaseFlowFragment<AuthViewModel>() {

    private val component: AuthComponent by lazy {
        DaggerAuthComponent
            .factory()
            .create(
                authDeps = findComponentDependencies(),
                fragmentManager = childFragmentManager
            )
    }

    private val viewModelFactory: MultiViewModelFactory by lazy {
        component.viewModelFactory()
    }

    override val viewModel: AuthViewModel by viewModels {
        viewModelFactory
    }

    override val flowRouter: FlowRouter by lazy {
        component.flowRouter()
    }

    private val biometricsActivityResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        logD("biometricsActivityResult ${it.data?.extras}")
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
    private fun BiometricStatusLaunchedEffect(
        biometricsStatus: BiometricsStatus?
    ) {
        val mostRecentStatus by rememberUpdatedState(biometricsStatus)
        LaunchedEffect(mostRecentStatus) {
            when (mostRecentStatus) {
                BiometricsStatus.SET -> {
                    viewModel.setDialogAnswer(BiometricsDialogClickState.Positive)
                }
                else -> {

                }
            }
        }
    }

    @Composable
    private fun FingerprintEventLaunchedEffect(
        fingerprintEvent: BiometricsState?
    ) {
        when (fingerprintEvent) {
            is BiometricsState.ShowPrompt -> {
                ShowEnableBiometricsDialog(true)
            }
            is BiometricsState.ShowNothing -> {
                ShowEnableBiometricsDialog(false)
            }
            else -> {

            }
        }
    }

    @Composable
    private fun AuthFragmentScreen() {
        val loading by viewModel.loading.observeAsState(false)
        val authData by viewModel.authFieldsData.collectAsState()
        val fingerprintEvent by viewModel.setFingerprintEvent.collectAsState(BiometricsState.ShowNothing)
        val biometricsIntent by viewModel.biometricsIntent.collectAsState(null)
        val biometricsStatus by viewModel.biometricsStatus.collectAsState(null)

        LaunchedEffect(biometricsIntent) {
            if (biometricsIntent != null) {
                biometricsActivityResult.launch(biometricsIntent)
            }
        }

        BiometricStatusLaunchedEffect(biometricsStatus)

        FingerprintEventLaunchedEffect(fingerprintEvent)
        LoaderDialogCompose(
            show = loading
        )

        AuthScreen(
            onSignInClick = viewModel::login,
            onRegisterClick = viewModel::goToRegisterFlow,
            phone = authData.phone,
            setPhone = viewModel::setPhone,
            password = authData.password,
            setPassword = viewModel::setPassword,
            isPhoneFullyEntered = authData.isPhoneFullyEntered,
            onImePasswordActionDone = viewModel::loginFromIme,
            onSettingsClick = viewModel::goToSettings,
            showBiometrics = biometricsStatus == BiometricsStatus.SET,
            onShowBiometricsClick = {
                viewModel.setDialogAnswer(BiometricsDialogClickState.Positive)
            }
        )
    }

    @Composable
    private fun ShowEnableBiometricsDialog(
        show: Boolean
    ) {
        AuthBiometricsDialog(
            open = show,
            onPositiveClick = {
                viewModel.setDialogAnswer(BiometricsDialogClickState.Positive)
            },
            onNegativeClick = {
                viewModel.setDialogAnswer(BiometricsDialogClickState.Negative)
            }
        )
    }

}