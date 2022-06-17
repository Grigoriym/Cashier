package com.grappim.feature.auth.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.grappim.common.lce.Try
import com.grappim.domain.storage.GeneralStorage
import com.grappim.feature.auth.domain.LoginUseCase
import com.grappim.feature.auth.presentation.model.BiometricsState
import com.grappim.feature.auth.presentation.ui.viewmodel.AuthViewModel
import com.grappim.feature.auth.presentation.ui.viewmodel.AuthViewModelImpl
import com.grappim.navigation.router.FlowRouter
import com.grappim.test_shared.CoroutineRule
import com.grappim.test_shared_android.getOrAwaitValue
import com.grappim.utils.biometric.BiometricPromptUtils
import com.grappim.workers.WorkerHelper
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class AuthViewModelImplTest {

    private companion object {
        private const val PHONE_FULL = "9999999999"
        private const val PHONE_PARTIAL = "99999"
        private const val PASSWORD = "asd"
    }

    @get:Rule
    val coroutineRule = CoroutineRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var workerHelperMock: WorkerHelper

    @MockK
    lateinit var loginUseCaseMock: LoginUseCase

    @MockK
    lateinit var generalStorageMock: GeneralStorage

    @RelaxedMockK
    lateinit var biometricPromptUtilsMock: BiometricPromptUtils

    @RelaxedMockK
    lateinit var flowRouterMock: FlowRouter

    private lateinit var viewModel: AuthViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = AuthViewModelImpl(
            loginUseCase = loginUseCaseMock,
            workerHelper = workerHelperMock,
            generalStorage = generalStorageMock,
            biometricPromptUtils = biometricPromptUtilsMock
        )

        initBlock()
    }

    @Test
    fun `creating viewModel authFieldsData is empty`() {
        val result = viewModel.authFieldsData.value
        result.shouldNotBeNull()
        result.phone shouldBeEqualTo ""
        result.password shouldBeEqualTo ""
        result.isPhoneFullyEntered shouldBeEqualTo false
    }

    @Test
    fun `calling setPassword sets password to authFieldsData`() {
        viewModel.setPassword(PASSWORD)
        viewModel.authFieldsData.value.password shouldBeEqualTo PASSWORD
    }

    @Test
    fun `calling setPhone sets phone to authFieldsData`() {
        viewModel.setPhone(PHONE_FULL)
        viewModel.authFieldsData.value.phone shouldBeEqualTo PHONE_FULL
    }

    @Test
    fun `calling setPhone with full phone sets isPhoneFullyEntered to true in authFieldsData`() {
        viewModel.setPhone(PHONE_FULL)
        viewModel.authFieldsData.value.phone shouldBeEqualTo PHONE_FULL
        viewModel.authFieldsData.value.isPhoneFullyEntered shouldBeEqualTo true
    }

    @Test
    fun `calling setPhone with partial phone sets isPhoneFullyEntered to false in authFieldsData`() {
        viewModel.setPhone(PHONE_PARTIAL)
        viewModel.authFieldsData.value.phone shouldBeEqualTo PHONE_PARTIAL
        viewModel.authFieldsData.value.isPhoneFullyEntered shouldBeEqualTo false
    }

    @Test
    fun `calling login with success sets ShowPrompt to fingerprintEvent `() = runTest {
        fillAuthFieldsWithData()
        success()
        launch {
            viewModel.setFingerprintEvent.test {
                awaitItem() shouldBe BiometricsState.ShowPrompt
                cancelAndIgnoreRemainingEvents()
            }
        }

        runCurrent()
        viewModel.login()
        runCurrent()
    }

    @Test
    fun `calling login with error sets throwable to error`() = runTest {
        fillAuthFieldsWithData()
        error()
        invoking { viewModel.error.getOrAwaitValue() } shouldThrow AnyException
        viewModel.login()
        runCurrent()
        viewModel.error.getOrAwaitValue().shouldNotBeNull()
    }

    @Test
    fun `calling loginFromIme with partial phone will not call loginUseCase`() = runTest {
        viewModel.setPassword(PASSWORD)
        viewModel.setPhone(PHONE_PARTIAL)
        viewModel.loginFromIme()
        runCurrent()
        coVerify {
            loginUseCaseMock wasNot Called
        }
    }

    @Test
    fun `calling loginFromIme with full phone will call loginUseCase`() = runTest {
        success()
        viewModel.setPassword(PASSWORD)
        viewModel.setPhone(PHONE_FULL)
        viewModel.loginFromIme()
        runCurrent()
        coVerify(exactly = 1) {
            loginUseCaseMock.login(any())
        }
    }

    @Test
    fun `calling goToRegisterFlow calls flowRouter goToSignUpFromSignIn`() {
        viewModel.setupFlowRouter(flowRouterMock)
        viewModel.goToRegisterFlow()
        verify(exactly = 1) {
            flowRouterMock.goToSignUpFromSignIn()
        }
    }

    @Test
    fun `calling goToSettings calls flowRouter goToSettings`() {
        viewModel.setupFlowRouter(flowRouterMock)
        viewModel.goToSettings()
        verify(exactly = 1) {
            flowRouterMock.goToSettings()
        }
    }

    private fun initBlock() {
        coEvery {
            generalStorageMock.setAuthErrorFlow(any())
        } just Runs
    }

    private fun success() {
        coEvery {
            loginUseCaseMock.login(any())
        } returns Try.Success(Unit)
    }

    private fun fillAuthFieldsWithData() {
        viewModel.setPhone(PHONE_FULL)
        viewModel.setPassword(PASSWORD)
    }

    private fun error() {
        coEvery {
            loginUseCaseMock.login(any())
        } returns Try.Error(IllegalStateException("error state"))
    }

}