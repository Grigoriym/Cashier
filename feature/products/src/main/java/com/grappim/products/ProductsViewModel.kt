package com.grappim.products

import androidx.lifecycle.*
import com.grappim.cashier.core.functional.WhileViewSubscribed
import com.grappim.domain.interactor.products.GetCategoryListUseCase
import com.grappim.domain.interactor.products.GetProductsByQueryUseCase
import com.grappim.domain.model.product.Category
import com.grappim.domain.model.product.Product
import com.grappim.navigation.Navigator
import com.zhuinden.livedatacombinetuplekt.combineTuple
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsByQueryUseCase: GetProductsByQueryUseCase,
    getCategoryListUseCase: GetCategoryListUseCase,
    private val navigator: Navigator
) : ViewModel() {

    val categories: StateFlow<List<Category>> =
        getCategoryListUseCase.execute2(
            GetCategoryListUseCase.Params()
        ).stateIn(
            scope = viewModelScope,
            started = WhileViewSubscribed,
            initialValue = emptyList()
        )

    private val _query = MutableLiveData("")
    val query: LiveData<String>
        get() = _query

    private val _selectedIndex = MutableStateFlow(0)
    val selectedIndex: StateFlow<Int>
        get() = _selectedIndex.asStateFlow()

    private val _selectedCategory = MutableLiveData<Category>()

    val products: LiveData<List<Product>> = combineTuple(
        _query,
        _selectedCategory
    ).switchMap { (query, category) ->
        getProductsByQueryUseCase.invoke(
            GetProductsByQueryUseCase.Params(
                category, query!!
            )
        ).asLiveData(viewModelScope.coroutineContext)
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            _query.value = query
        }
    }

    fun setCategory(category: Category, index: Int) {
        viewModelScope.launch {
            _selectedCategory.value = category
            _selectedIndex.value = index
        }
    }

}