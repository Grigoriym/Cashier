package com.grappim.stock.ui.viewmodel

import app.cash.turbine.test
import com.grappim.common.lce.Try
import com.grappim.domain.interactor.stock.getStocks.GetStocksUseCase
import com.grappim.domain.interactor.stock.saveStock.SaveStockUseCase
import com.grappim.domain.model.outlet.Stock
import com.grappim.domain.repository.local.SelectStockLocalRepository
import com.grappim.domain.storage.GeneralStorage
import com.grappim.test_shared.CoroutineRule
import com.grappim.test_shared.testException
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class SelectStockViewModelImplTest {

//    @get:Rule
//    val coroutineRule = CoroutineRule()
//
//    @get:Rule
//    var rule: TestRule = InstantTaskExecutorRule()
//
//    @MockK
//    lateinit var getStocksUseCase: GetStocksUseCase
//
//    @MockK
//    lateinit var saveStockUseCase: SaveStockUseCase
//
//    @RelaxedMockK
//    lateinit var selectStockLocalRepository: SelectStockLocalRepository
//
//    @MockK
//    lateinit var generalStorage: GeneralStorage
//
//    private lateinit var viewModel: SelectStockViewModel
//
//    private val testStock = Stock(
//        name = "name",
//        merchantId = "merchantId",
//        stockId = "stockId"
//    )
//
//    private val stocksList = listOf(testStock)
//
//    @Before
//    fun setup() {
//        MockKAnnotations.init(this)
//
//        viewModel = SelectStockViewModelImpl(
//            getStocksUseCase = getStocksUseCase,
//            saveStockUseCase = saveStockUseCase,
//            selectStockLocalRepository = selectStockLocalRepository,
//            generalStorage = generalStorage
//        )
//    }
//
//    @Test
//    fun `creating viewModel calls getStocks with success results in Success`() = runTest {
//        coEvery {
//            getStocksUseCase.execute()
//        } returns Try.Success(stocksList)
//
//        launch {
//            viewModel.stocksResult.test {
//                awaitItem() shouldBe stocksList
//                cancelAndIgnoreRemainingEvents()
//            }
//        }
//
//        runCurrent()
//
//        coVerify(exactly = 1) {
//            getStocksUseCase.execute()
//        }
//    }
//
//    @Test
//    fun `creating viewModel calls getStocks with error results in Error`() = runTest {
//        coEvery {
//            getStocksUseCase.execute()
//        } returns Try.Error(testException)
//
//        invoking {
//            viewModel.error.getOrAwaitValue()
//        } shouldThrow AnyException
//
//        runCurrent()
//
//        coVerify(exactly = 1) {
//            getStocksUseCase.execute()
//        }
//        viewModel.error.getOrAwaitValue().shouldNotBeNull()
//    }
//
//    @Test
//    fun `calling clearData calls generalStorage clearData`() = runTest {
//        viewModel.clearData()
//        runCurrent()
//        coVerify(exactly = 1) {
//            generalStorage.clearData()
//        }
//    }
//
//    @Test
//    fun `calling saveStock call saveStockUseCase`() = runTest {
//        runCurrent()
//        viewModel.saveStock()
//        runCurrent()
//
//        coVerify(exactly = 1) {
//            saveStockUseCase.execute(any())
//        }
//    }
//
//    @Test
//    fun `calling selectStock sets the stock to selectedStock`() = runTest {
//        every {
//            selectStockLocalRepository.getSelectedStock()
//        } returns testStock
//        runCurrent()
//        viewModel.selectStock(testStock)
//        runCurrent()
//
//        launch {
//            viewModel.selectedStock.test {
//                awaitItem() shouldBe testStock
//                cancelAndIgnoreRemainingEvents()
//            }
//        }
//
//        verify(exactly = 1) {
//            selectStockLocalRepository.setSelectedStock(any())
//        }
//    }
}