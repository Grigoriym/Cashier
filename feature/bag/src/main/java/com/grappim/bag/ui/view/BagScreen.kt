package com.grappim.bag.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.bag.R
import com.grappim.domain.model.basket.BasketProduct
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.CashierText
import com.grappim.uikit.compose.ItemAddBasketProductCompose
import com.grappim.uikit.compose.button.StandardFilledButton
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierTheme

@Composable
fun BagScreen(
    onBackClick: () -> Unit,
    onScanClick: () -> Unit,
    onPayClick: () -> Unit,
    onMinusClick: (BasketProduct) -> Unit,
    onPlusClick: (BasketProduct) -> Unit,
    items: List<BasketProduct>,
    price: String,
    changedProduct: BasketProduct?
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            BaseTopAppBar(
                toolbarTitle = stringResource(id = R.string.title_bag),
                backButtonAction = onBackClick
            )
        },
        bottomBar = {
            BottomSegment(
                onScanClick = onScanClick,
                onPayClick = onPayClick,
                itemSize = items.size,
                price = price
            )
        }
    ) {
        MainSegment(
            items = items,
            onMinusClick = onMinusClick,
            onPlusClick = onPlusClick,
            changedProduct = changedProduct
        )
    }
}

@Composable
private fun MainSegment(
    items: List<BasketProduct>,
    onMinusClick: (BasketProduct) -> Unit,
    onPlusClick: (BasketProduct) -> Unit,
    changedProduct: BasketProduct?
) {
    Column {
        BagListSegment(
            items = items,
            onMinusClick = onMinusClick,
            onPlusClick = onPlusClick,
            changedProduct = changedProduct
        )
    }
}

@Composable
private fun BagListSegment(
    items: List<BasketProduct>,
    onMinusClick: (BasketProduct) -> Unit,
    onPlusClick: (BasketProduct) -> Unit,
    changedProduct: BasketProduct?
) {
    LazyColumn() {
        items(items) { item ->
            if (item.id == changedProduct?.id) {
                ItemAddBasketProductCompose(
                    basketProduct = changedProduct,
                    onMinusClick = onMinusClick,
                    onPlusClick = onPlusClick,
                    showCart = false
                )
            } else {
                ItemAddBasketProductCompose(
                    basketProduct = item,
                    onMinusClick = onMinusClick,
                    onPlusClick = onPlusClick,
                    showCart = false
                )
            }
        }
    }
}

@Composable
private fun BottomSegment(
    onScanClick: () -> Unit,
    onPayClick: () -> Unit,
    itemSize: Int,
    price: String
) {
    Surface(
        modifier = Modifier,
        elevation = 16.dp
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 32.dp
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 10.dp
                    )
            ) {
                CashierText(
                    text = stringResource(id = R.string.title_item1),
                    modifier = Modifier
                        .weight(1f),
                    textAlign = TextAlign.Start
                )

                CashierText(
                    text = "$itemSize",
                    color = CashierBlue,
                    modifier = Modifier
                        .weight(1f),
                    textAlign = TextAlign.End
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = 10.dp
                    )
            ) {
                CashierText(
                    text = stringResource(id = R.string.title_price1),
                    modifier = Modifier
                        .weight(1f),
                    textAlign = TextAlign.Start
                )
                CashierText(
                    text = stringResource(
                        id = R.string.title_price_with_currency,
                        price
                    ),
                    color = CashierBlue,
                    modifier = Modifier
                        .weight(1f),
                    textAlign = TextAlign.End
                )
            }

            Row(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    )
            ) {
                StandardFilledButton(
                    onButtonClick = onPayClick,
                    iconDrawable = R.drawable.ic_shopping_cart,
                    modifier = Modifier
                        .weight(2f)
                        .padding(
                            start = 8.dp
                        ),
                    backgroundColor = CashierBlue,
                    text = stringResource(id = R.string.action_pay),
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
}

@Composable
@Preview(
    showBackground = true
)
private fun BottomSegmentPreview() {
    CashierTheme {
        BottomSegment(
            onScanClick = { },
            onPayClick = { },
            itemSize = 6,
            price = "500"
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun BagScreenPreview() {
    CashierTheme {
        BagScreen(
            onBackClick = { },
            onScanClick = { },
            onPayClick = { },
            onMinusClick = {},
            onPlusClick = {},
            items = listOf(BasketProduct.empty()),
            price = "500",
            changedProduct = null
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun BagListSegmentPreview() {
    CashierTheme {
        BagListSegment(
            items = listOf(BasketProduct.empty()),
            onMinusClick = {},
            onPlusClick = {},
            changedProduct = null
        )
    }
}