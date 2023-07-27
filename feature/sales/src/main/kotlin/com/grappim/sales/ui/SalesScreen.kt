package com.grappim.sales.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.grappim.domain.model.BasketProduct
import com.grappim.domain.model.Product
import com.grappim.uikit.R
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.ItemAddBasketProductCompose
import com.grappim.uikit.compose.ItemAddProductCompose
import com.grappim.uikit.compose.button.StandardFilledButton
import com.grappim.uikit.compose.text_field.CashierSearchTextField
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierTheme
import kotlinx.coroutines.flow.flowOf

@Composable
internal fun SalesScreen(
    onBackClick: () -> Unit,
    onScanClick: () -> Unit,
    onBagClick: () -> Unit,
    bagCount: String,
    items: LazyPagingItems<Product>,
    onMinusClick: (Product) -> Unit,
    onPlusClick: (Product) -> Unit,
    onCartClick: (Product) -> Unit,
    searchText: String,
    setSearchText: (String) -> Unit,
    basketProducts: List<BasketProduct>
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            BaseTopAppBar(
                toolbarTitle = stringResource(id = R.string.title_sales),
                backButtonAction = onBackClick
            )
        },
        bottomBar = {
            BottomBarSegment(
                onScanClick = onScanClick,
                onBagClick = onBagClick,
                bagCount = bagCount
            )
        }
    ) {
        SalesScreenMainSegment(
            modifier = Modifier.padding(it),
            searchText = searchText,
            setSearchText = setSearchText,
            items = items,
            onMinusClick = onMinusClick,
            onPlusClick = onPlusClick,
            onCartClick = onCartClick,
            basketProducts = basketProducts
        )
    }
}

@Composable
private fun SalesScreenMainSegment(
    modifier: Modifier = Modifier,
    searchText: String,
    setSearchText: (String) -> Unit,
    items: LazyPagingItems<Product>,
    onMinusClick: (Product) -> Unit,
    onPlusClick: (Product) -> Unit,
    onCartClick: (Product) -> Unit,
    basketProducts: List<BasketProduct>
) {
    Column(
        modifier = modifier
    ) {
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

        SalesListSegment(
            items = items,
            onMinusClick = onMinusClick,
            onPlusClick = onPlusClick,
            onCartClick = onCartClick,
            basketProducts = basketProducts
        )
    }
}

@Composable
private fun SalesListSegment(
    items: LazyPagingItems<Product>,
    onMinusClick: (Product) -> Unit,
    onPlusClick: (Product) -> Unit,
    onCartClick: (Product) -> Unit,
    basketProducts: List<BasketProduct>
) {
    LazyColumn {
        items(items.itemCount) { index ->
            val item = items[index]
            item?.let {
                if (item.basketProduct != null) {
                    ItemAddBasketProductCompose(
                        basketProduct = item.basketProduct!!,
                        onMinusClick = {
                            onMinusClick(item)
                        },
                        onPlusClick = {
                            onPlusClick(item)
                        }
                    )
                } else {
                    ItemAddProductCompose(
                        product = item,
                        onMinusClick = onMinusClick,
                        onPlusClick = onPlusClick,
                        onCartClick = onCartClick
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomBarSegment(
    onScanClick: () -> Unit,
    onBagClick: () -> Unit,
    bagCount: String
) {
    Surface(
        modifier = Modifier,
        elevation = 16.dp
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 32.dp,
                    top = 16.dp
                )
        ) {
            StandardFilledButton(
                onButtonClick = onBagClick,
                iconDrawable = R.drawable.ic_shopping_cart,
                modifier = Modifier
                    .weight(2f)
                    .padding(
                        start = 8.dp
                    ),
                backgroundColor = CashierBlue,
                text = bagCount,
                iconTint = Color.White
            )
            StandardFilledButton(
                onButtonClick = onScanClick,
                iconDrawable = R.drawable.ic_barcode,
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        start = 8.dp
                    ),
                backgroundColor = Color.White,
                iconTint = CashierBlue
            )
        }
    }
}

@Composable
@Preview
private fun SalesScreenPreview() {
    CashierTheme {
        val items = flowOf(
            PagingData.empty<Product>()
        ).collectAsLazyPagingItems()
        SalesScreen(
            onBackClick = { },
            onScanClick = { },
            onBagClick = { },
            bagCount = "5",
            items = items,
            onMinusClick = {},
            onCartClick = {},
            onPlusClick = {},
            searchText = "123",
            setSearchText = {},
            basketProducts = emptyList()
        )
    }
}

@Composable
@Preview
private fun BottomBarSegmentPreview() {
    CashierTheme {
        BottomBarSegment(
            onScanClick = {},
            onBagClick = {},
            bagCount = "5"
        )
    }
}