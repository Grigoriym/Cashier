package com.grappim.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grappim.domain.model.product.Category
import com.grappim.domain.model.product.Product
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.BigActionButtonCompose
import com.grappim.uikit.compose.ItemProductCompose
import com.grappim.uikit.compose.OutlinedTextFieldCompose
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierGray
import com.grappim.uikit.theme.CashierTheme
import kotlin.reflect.KFunction2

@Composable
internal fun ProductsScreen(
    onBackPressed: () -> Unit,
    onCreateProductClick: () -> Unit,
    searchText: String,
    setSearchText: (String) -> Unit,
    categories: List<Category>,
    selectedIndex: Int,
    onTabClick: (Int, Category) -> Unit,
    products: List<Product>,
    onProductClick: (Product) -> Unit
) {
    Scaffold(
        modifier = Modifier,
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
                modifier = Modifier,
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
    categories: List<Category>,
    selectedIndex: Int,
    onTabClick: (Int, Category) -> Unit,
    products: List<Product>,
    onProductClick: (Product) -> Unit
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
    categories: List<Category>,
    selectedIndex: Int,
    onTabClick: (Int, Category) -> Unit
) {
    if (categories.isNotEmpty()) {
        ScrollableTabRow(
            selectedTabIndex = selectedIndex,
            backgroundColor = Color.White,
            contentColor = Color.White,
            edgePadding = 16.dp
        ) {
            categories.forEachIndexed { index, category ->
                Tab(
                    selected = selectedIndex == index,
                    onClick = {
                        onTabClick(index, category)
                    },
                    text = {
                        Text(text = category.name)
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
@Preview
private fun ProductsScreenPreview() {
    CashierTheme {
        ProductsScreen(
            onBackPressed = {},
            onCreateProductClick = {},
            searchText = "",
            setSearchText = {},
            categories = listOf(Category.empty(), Category.empty()),
            selectedIndex = 0,
            onTabClick = { index, category ->

            },
            products = emptyList(),
            onProductClick = {}
        )
    }
}