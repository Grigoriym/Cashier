package com.grappim.products.presentation.list.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.grappim.domain.model.Product
import com.grappim.feature.products.domain.interactor.getProductsByQuery.GetProductsByQueryUseCase
import com.grappim.productcategory.domain.interactor.getCategoryList.GetCategoryListUseCase
import com.grappim.productcategory.domain.model.ProductCategory
import com.grappim.test_shared.CoroutineRule
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ProductListViewModelImplTes {

    private companion object {
        private const val SEARCH_QUERY = "query"
    }

    @get:Rule
    val coroutineRule = CoroutineRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var getCategoryListUseCase: GetCategoryListUseCase

    @MockK
    lateinit var getProductsByQueryUseCase: GetProductsByQueryUseCase

    private lateinit var viewModel: ProductListViewModel

    private val productList = listOf(
        Product.empty()
    )

    private val productCategoryList = listOf(
        ProductCategory.empty()
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        viewModel = ProductListViewModelImpl(
            getCategoryListUseCase = getCategoryListUseCase,
            getProductsByQueryUseCase = getProductsByQueryUseCase
        )
    }

    @Test
    fun `calling searchProducts will return products List`() = runTest {
        coEvery {
            getProductsByQueryUseCase.execute(any())
        } returns flowOf(productList)
        coEvery {
            getCategoryListUseCase.getSimpleCategoryList(any())
        } returns flowOf(productCategoryList)

        launch {
            viewModel.query.test {
                awaitItem().shouldBeEmpty()

                awaitItem() shouldBeEqualTo SEARCH_QUERY

                cancelAndIgnoreRemainingEvents()
            }
        }

        launch {
            viewModel.products.test {
                awaitItem() shouldBeEqualTo productList

                cancelAndIgnoreRemainingEvents()
            }
        }

        viewModel.searchProducts(SEARCH_QUERY)
        runCurrent()

        coVerify(exactly = 1) {
            getProductsByQueryUseCase.execute(any())
        }
    }
}