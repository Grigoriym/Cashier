package com.grappim.cashbox.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.grappim.cashier.common.lce.Try
import com.grappim.cashier.testshared.CoroutineRule
import com.grappim.domain.interactor.cashbox.getCashbox.GetCashBoxesUseCase
import com.grappim.domain.interactor.cashbox.saveCashbox.SaveCashBoxUseCase
import com.grappim.domain.model.cashbox.CashBox
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule

class SelectCashBoxViewModelImplTest {

    @get:Rule
    val coroutineRule = CoroutineRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK
    lateinit var getCashBoxesUseCase: GetCashBoxesUseCase

    @MockK
    lateinit var saveCashBoxUseCase: SaveCashBoxUseCase

    private lateinit var viewModel: SelectCashBoxViewModel

    private val testCashBox = CashBox(
        name = "name",
        cashBoxId = "cashBoxId",
        merchantId = "merchantId",
        stockId = "stockId"
    )

    private val testCashBoxList = listOf(testCashBox)

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = SelectCashBoxViewModelImpl(
            getCashBoxesUseCase = getCashBoxesUseCase,
            saveCashBoxUseCase = saveCashBoxUseCase
        )
    }

//    @Test
//    fun `creating viewModel calls getCashBoxes with success results in Success`() = runTest {
//        getCashBoxesSuccess()
//
//        runCurrent()
//
//        coVerify(exactly = 1) {
//            getCashBoxesUseCase.execute()
//        }
//        viewModel.cashBoxes.shouldNotBeEmpty()
//        viewModel.cashBoxes.shouldContainSame(testCashBoxList)
//    }

//    @Test
//    fun `creating viewModel calls getCashBoxes with error results in Error`() = runTest {
//        coEvery {
//            getCashBoxesUseCase.execute()
//        } returns Try.Error(testException)
//        invoking {
//            viewModel.error.getOrAwaitValue()
//        } shouldThrow AnyException
//
//        runCurrent()
//
//        coVerify(exactly = 1) {
//            getCashBoxesUseCase.execute()
//        }
//        viewModel.error.getOrAwaitValue().shouldNotBeNull()
//    }

    @Suppress("UnusedPrivateProperty", "UnusedPrivateMember")
    private fun getCashBoxesSuccess() {
        coEvery {
            getCashBoxesUseCase.execute()
        } returns Try.Success(testCashBoxList)
    }
}
