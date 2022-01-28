package com.grappim.payment_method.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.domain.model.payment.PaymentMethodType
import com.grappim.payment_method.R
import com.grappim.payment_method.model.PaymentMethod
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierTheme

@Composable
fun PaymentMethodScreen(
    onBackClick: () -> Unit,
    itemCount: String,
    basketPrice: String,
    menuItems: List<PaymentMethod>,
    onItemClick: (PaymentMethod) -> Unit
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            BaseTopAppBar(
                toolbarTitle = stringResource(id = R.string.title_payment_method),
                backButtonAction = onBackClick
            )
        },
        bottomBar = {
            BottomBarSegment(
                itemCount = itemCount,
                basketPrice = basketPrice
            )
        }
    ) {
        MainSegment(
            menuItems = menuItems,
            onItemClick = onItemClick
        )
    }
}

@Composable
private fun MainSegment(
    menuItems: List<PaymentMethod>,
    onItemClick: (PaymentMethod) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(
            start = 16.dp,
            top = 10.dp,
            end = 16.dp,
            bottom = 10.dp
        )
    ) {
        items(menuItems) { item ->
            Card(
                modifier = Modifier
                    .fillParentMaxWidth(),
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    onItemClick(item)
                },
                backgroundColor = Color.White,
                indication = rememberRipple()
            ) {
                Box(
                    modifier = Modifier
                        .padding(all = 24.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = item.text),
                        modifier = Modifier
                            .align(Alignment.Center),
                        fontSize = 22.sp,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomBarSegment(
    itemCount: String,
    basketPrice: String
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
                Text(
                    text = stringResource(id = R.string.title_item1),
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .weight(1f),
                    textAlign = TextAlign.Start
                )

                Text(
                    text = itemCount,
                    fontSize = 16.sp,
                    color = CashierBlue,
                    modifier = Modifier
                        .weight(1f),
                    textAlign = TextAlign.End
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.title_price1),
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .weight(1f),
                    textAlign = TextAlign.Start
                )
                Text(
                    text = stringResource(
                        id = R.string.title_price_with_currency,
                        basketPrice
                    ),
                    fontSize = 16.sp,
                    color = CashierBlue,
                    modifier = Modifier
                        .weight(1f),
                    textAlign = TextAlign.End
                )
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun BottomSegmentPreview(){
    CashierTheme {
        BottomBarSegment(
            itemCount = "5",
            basketPrice = "345"
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun PaymentMethodScreenPreview() {
    CashierTheme {
        PaymentMethodScreen(
            onBackClick = { },
            itemCount = "4",
            basketPrice = "123",
            menuItems = listOf(
                PaymentMethod(
                    text = R.string.title_cash_payment,
                    type = PaymentMethodType.CASH
                ),
                PaymentMethod(
                    text = R.string.title_card_payment,
                    type = PaymentMethodType.CARD
                )
            ),
            onItemClick = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun MainSegmentPreview() {
    CashierTheme {
        MainSegment(
            menuItems = listOf(
                PaymentMethod(
                    text = R.string.title_cash_payment,
                    type = PaymentMethodType.CASH
                ),
                PaymentMethod(
                    text = R.string.title_card_payment,
                    type = PaymentMethodType.CARD
                )
            ),
            onItemClick = {})
    }
}