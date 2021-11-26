package com.grappim.waybill.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.grappim.domain.model.product.Product
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.ItemProductCompose
import com.grappim.uikit.compose.OutlinedTextFieldCompose
import com.grappim.waybill.R

@Composable
fun SearchProductScreen(
    onBackClick: () -> Unit,
    searchText: String,
    setSearchText: (String) -> Unit,
    products: List<Product>,
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
        Column {
            OutlinedTextFieldCompose(
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
                    ItemProductCompose(
                        product = item,
                        onProductClick = onProductClick
                    )
                }
            }
        }
    }
}