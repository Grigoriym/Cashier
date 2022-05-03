package com.grappim.products.presentation.list.ui.view

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grappim.domain.model.product.Product
import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.products.presentation.R
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.CashierText
import com.grappim.uikit.compose.ItemProductCompose
import com.grappim.uikit.compose.button.BigActionButtonCompose
import com.grappim.uikit.compose.text_field.CashierSearchTextField
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierGray
import com.grappim.uikit.theme.CashierTheme

@Composable
internal fun ProductsScreen(
    onBackPressed: () -> Unit,
    onCreateProductClick: () -> Unit,
    searchText: String,
    setSearchText: (String) -> Unit,
    categories: List<ProductCategory>,
    selectedIndex: Int,
    onTabClick: (Int, ProductCategory) -> Unit,
    products: List<Product>,
    onProductClick: (Product) -> Unit
) {
    Scaffold(
        topBar = {
            BaseTopAppBar(
                toolbarTitle = stringResource(id = R.string.title_products)
            ) {
                onBackPressed()
            }
        },
        bottomBar = {
            BigActionButtonCompose(
                buttonText = stringResource(id = R.string.action_create_product),
                onButtonClick = onCreateProductClick
            )
        }
    ) {
        ProductsScreenMainSegment(
            searchText = searchText,
            setSearchText = setSearchText,
            categories = categories,
            selectedIndex = selectedIndex,
            onTabClick = onTabClick,
            products = products,
            onProductClick = onProductClick
        )
    }
}

@Composable
private fun ProductsScreenMainSegment(
    searchText: String,
    setSearchText: (String) -> Unit,
    categories: List<ProductCategory>,
    selectedIndex: Int,
    onTabClick: (Int, ProductCategory) -> Unit,
    products: List<Product>,
    onProductClick: (Product) -> Unit
) {
    Column {
        CashierSearchTextField(
            modifier = Modifier
                .padding(
                    top = 24.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            text = searchText,
            onTextChange = setSearchText
        )

        CategoriesSegment(
            categories = categories,
            selectedIndex = selectedIndex,
            onTabClick = onTabClick
        )

        ProductListSegment(
            products = products,
            onProductClick = onProductClick
        )
    }
}

@Composable
private fun CategoriesSegment(
    categories: List<ProductCategory>,
    selectedIndex: Int,
    onTabClick: (Int, ProductCategory) -> Unit
) {
    if (categories.isNotEmpty()) {
        ScrollableTabRow(
            selectedTabIndex = selectedIndex,
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.background,
            edgePadding = 16.dp
        ) {
            categories.forEachIndexed { index, category ->
                val selected = selectedIndex == index
                val textColor = if (selected) {
                    CashierBlue
                } else {
                    CashierGray
                }
                Tab(
                    selected = selected,
                    onClick = {
                        onTabClick(index, category)
                    },
                    text = {
                        CashierText(
                            text = category.name,
                            color = textColor
                        )
                    },
                    selectedContentColor = CashierBlue,
                    unselectedContentColor = CashierGray
                )
            }
        }
    }
}

@Composable
private fun ProductListSegment(
    products: List<Product>,
    onProductClick: (Product) -> Unit
) {
    LazyColumn() {
        items(products) { item ->
            ItemProductCompose(
                product = item,
                onProductClick = onProductClick
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
private fun ProductsScreenPreview() {
    CashierTheme {
        ProductsScreen(
            onBackPressed = {},
            onCreateProductClick = {},
            searchText = "search text",
            setSearchText = {},
            categories = listOf(ProductCategory.empty(), ProductCategory.empty()),
            selectedIndex = 0,
            onTabClick = { index, category ->

            },
            products = listOf(Product.empty(), Product.empty()),
            onProductClick = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
private fun ProductsScreenNightPreview() {
    CashierTheme {
        ProductsScreen(
            onBackPressed = {},
            onCreateProductClick = {},
            searchText = "search text",
            setSearchText = {},
            categories = listOf(ProductCategory.empty(), ProductCategory.empty()),
            selectedIndex = 0,
            onTabClick = { index, category ->

            },
            products = listOf(Product.empty(), Product.empty()),
            onProductClick = {}
        )
    }
}