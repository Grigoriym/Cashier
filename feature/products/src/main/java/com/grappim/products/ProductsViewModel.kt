package com.grappim.products

import androidx.lifecycle.*
import com.grappim.domain.base.Result
import com.grappim.domain.interactor.products.GetCategoryListUseCase
import com.grappim.domain.interactor.products.GetProductsByQueryUseCase
import com.grappim.domain.model.product.Category
import com.grappim.domain.model.product.Product
import com.zhuinden.livedatacombinetuplekt.combineTuple
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsByQueryUseCase: GetProductsByQueryUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase
) : ViewModel() {

    private val _categories: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>>
        get() = _categories

    private val _query = MutableLiveData("")

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

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoryListUseCase.invoke(GetCategoryListUseCase.Params())
                .collect {
                    if (it is Result.Success) {
                        _categories.value = it.data!!
                    }
                }
        }
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            _query.value = query
        }
    }

    fun setCategory(category: Category) {
        viewModelScope.launch {
            _selectedCategory.value = category
        }
    }

}