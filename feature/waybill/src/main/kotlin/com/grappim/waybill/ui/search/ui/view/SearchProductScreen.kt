package com.grappim.waybill.ui.search.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.grappim.domain.model.product.Product
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.ItemProductCompose
import com.grappim.uikit.compose.text_field.CashierOutlinedTextField
import com.grappim.uikit.theme.CashierTheme
import com.grappim.waybill.R
import kotlinx.coroutines.flow.flowOf

@Composable
fun SearchProductScreen(
    onBackClick: () -> Unit,
    searchText: String,
    setSearchText: (String) -> Unit,
    products: LazyPagingItems<Product>,
    onProductClick: (Product) -> Unit
) {
    Scaffold(
        topBar = {
            BaseTopAppBar(
                toolbarTitle = stringResource(id = R.string.product_search_product),
                backButtonAction = onBackClick
            )
        }
    ) {
        MainSegment(
            searchText = searchText,
            setSearchText = setSearchText,
            products = products,
            onProductClick = onProductClick
        )
    }
}

@Composable
private fun MainSegment(
    searchText: String,
    setSearchText: (String) -> Unit,
    products: LazyPagingItems<Product>,
    onProductClick: (Product) -> Unit
) {
    Column {
        CashierOutlinedTextField(
            modifier = Modifier
                .padding(
                    top = 24.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            text = searchText,
            onTextChange = setSearchText
        )
        LazyColumn() {
            items(products) { item ->
                item?.let {
                    ItemProductCompose(
                        product = item,
                        onProductClick = onProductClick
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun MainSegmentPreview() {
    CashierTheme {
        val items = flowOf(
            PagingData.empty<Product>()
        ).collectAsLazyPagingItems()
        MainSegment(
            searchText = "",
            setSearchText = {},
            products = items,
            onProductClick = {}
        )
    }
}