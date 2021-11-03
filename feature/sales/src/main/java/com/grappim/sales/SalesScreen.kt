package com.grappim.sales

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grappim.domain.model.product.Product
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.ItemAddProductCompose
import com.grappim.uikit.compose.OutlinedTextFieldCompose
import com.grappim.uikit.compose.StandardFilledButton
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierTheme

@Composable
internal fun SalesScreen(
    onBackClick: () -> Unit,
    onScanClick: () -> Unit,
    onBagClick: () -> Unit,
    bagCount: String,
    items: List<Product>,
    onMinusClick: (Product) -> Unit,
    onPlusClick: (Product) -> Unit,
    onCartClick: (Product) -> Unit,
    searchText: String,
    setSearchText: (String) -> Unit,
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
            searchText = searchText,
            setSearchText = setSearchText,
            items = items,
            onMinusClick = onMinusClick,
            onPlusClick = onPlusClick,
            onCartClick = onCartClick
        )
    }
}

@Composable
private fun SalesScreenMainSegment(
    searchText: String,
    setSearchText: (String) -> Unit,
    items: List<Product>,
    onMinusClick: (Product) -> Unit,
    onPlusClick: (Product) -> Unit,
    onCartClick: (Product) -> Unit
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

        SalesListSegment(
            items = items,
            onMinusClick = onMinusClick,
            onPlusClick = onPlusClick,
            onCartClick = onCartClick
        )
    }
}

@Composable
private fun SalesListSegment(
    items: List<Product>,
    onMinusClick: (Product) -> Unit,
    onPlusClick: (Product) -> Unit,
    onCartClick: (Product) -> Unit
) {
    LazyColumn() {
        items(items) { item ->
            ItemAddProductCompose(
                product = item,
                onMinusClick = onMinusClick,
                onPlusClick = onPlusClick,
                onCartClick = onCartClick
            )
        }
    }
}

@Composable
private fun BottomBarSegment(
    onScanClick: () -> Unit,
    onBagClick: () -> Unit,
    bagCount: String
) {
    Surface {
        Row(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp
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
                backgroundColor = Color.White
            )
        }
    }
}

@Composable
@Preview
private fun SalesScreenPreview() {
    CashierTheme {
        val items = listOf(
            Product.empty()
        )
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
            setSearchText = {}
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