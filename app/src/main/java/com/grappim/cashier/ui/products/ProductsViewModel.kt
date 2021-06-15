package com.grappim.cashier.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.grappim.cashier.core.functional.onFailure
import com.grappim.cashier.core.functional.onSuccess
import com.grappim.cashier.data.db.entity.CategoryEntity
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.domain.products.GetCategoryListUseCase
import com.grappim.cashier.domain.products.GetProductsByQueryUseCase
import com.zhuinden.livedatacombinetuplekt.combineTuple
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsByQueryUseCase: GetProductsByQueryUseCase,
    private val getCategoryListUseCase: GetCategoryListUseCase
) : ViewModel() {

    private val _categories: MutableLiveData<List<CategoryEntity>> = MutableLiveData()
    val categories: LiveData<List<CategoryEntity>>
        get() = _categories

    private val _query = MutableLiveData("")

    private val _selectedCategory = MutableLiveData<CategoryEntity>()

    val products: LiveData<List<ProductEntity>> = Transformations.switchMap(
        combineTuple(
            _query,
            _selectedCategory
        )
    ) { (query, category) ->
        getProductsByQueryUseCase.invoke(category, query!!)
            .asLiveData(viewModelScope.coroutineContext)
    }

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoryListUseCase.invoke()
                .onFailure {

                }.onSuccess {
                    _categories.value = it
                }
        }
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            _query.value = query
        }
    }

    fun setCategory(categoryEntity: CategoryEntity) {
        viewModelScope.launch {
            _selectedCategory.value = categoryEntity
        }
    }

}