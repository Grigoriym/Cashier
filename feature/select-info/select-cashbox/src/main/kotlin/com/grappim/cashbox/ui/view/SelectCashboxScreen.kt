package com.grappim.cashbox.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.grappim.cashbox.R
import com.grappim.cashbox.model.CashierProgressItem
import com.grappim.domain.model.cashbox.CashBox
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.CashierIcon
import com.grappim.uikit.compose.button.BigActionButtonCompose
import com.grappim.uikit.theme.*

@Composable
fun SelectCashBoxScreen(
    onBackButtonPressed: () -> Unit,
    cashBoxProgressItems: List<CashierProgressItem>,
    cashBoxItems: List<CashBox>,
    onRefresh: () -> Unit,
    selectCashBox: (CashBox) -> Unit,
    onNextClick: () -> Unit,
    isLoading: Boolean,
    selectedCashBox: CashBox? = null
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            BaseTopAppBar(
                toolbarTitle = ""
            ) {
                onBackButtonPressed()
            }
        },
        bottomBar = {
            BigActionButtonCompose(
                buttonText = stringResource(id = R.string.action_next),
                onButtonClick = onNextClick,
                isEnabled = selectedCashBox != null
            )
        }
    ) {
        Column(
            modifier = Modifier
        ) {
            Text(
                text = stringResource(id = R.string.title_checkout_selection),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(
                        top = 32.dp,
                        start = 16.dp
                    )
            )
            LazyRow(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp
                    )
            ) {
                items(cashBoxProgressItems) { item ->
                    CashBoxProgressItem(
                        item = item,
                        modifier = Modifier
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            CashBoxListSegment(
                cashBoxItems = cashBoxItems,
                selectedCashBox = selectedCashBox,
                onCashBoxClick = selectCashBox,
                isRefreshing = isLoading,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    ),
                onRefresh = onRefresh
            )
        }
    }
}

@Composable
private fun CashBoxListSegment(
    modifier: Modifier = Modifier,
    cashBoxItems: List<CashBox>,
    selectedCashBox: CashBox?,
    onCashBoxClick: (CashBox) -> Unit,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(
            isRefreshing = isRefreshing
        ),
        onRefresh = onRefresh
    ) {
        LazyColumn(modifier = modifier) {
            items(cashBoxItems) { item ->
                CashBoxListItem(
                    cashBox = item,
                    selectedCashBox = selectedCashBox,
                    onCashBoxClick = onCashBoxClick
                )
                Divider(
                    thickness = 1.dp,
                    color = CashierLightGray
                )
            }
        }
    }
}

@Composable
private fun CashBoxListItem(
    cashBox: CashBox,
    selectedCashBox: CashBox?,
    onCashBoxClick: (CashBox) -> Unit
) {
    val isItemSelected = cashBox.cashBoxId == selectedCashBox?.cashBoxId &&
      cashBox.name == selectedCashBox.name

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCashBoxClick(cashBox)
            }
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 10.dp,
                bottom = 10.dp
            )
    ) {
        Text(
            text = cashBox.name,
            modifier = Modifier.weight(1f)
        )
        Box(
            modifier = Modifier
                .size(24.dp)
        ) {
            if (isItemSelected) {
                CashierIcon(
                    imageVector = Icons.Filled.CheckCircle,
                    tint = CashierGreen,
                    modifier = Modifier
                        .size(24.dp)
                )
            } else {
                Spacer(modifier = Modifier)
            }
        }
    }
}

@Composable
private fun CashBoxProgressItem(
    item: CashierProgressItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        val color = if (item.isActive) {
            CashierBlue
        } else {
            CashierGray
        }

        CashierIcon(
            imageVector = Icons.Filled.CheckCircle,
            tint = color,
            modifier = Modifier
                .padding(start = 16.dp)
                .size(20.dp)
        )

        Text(
            text = stringResource(id = item.text),
            color = color,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(
                    start = 8.dp,
                    top = 8.dp,
                    end = 8.dp
                )
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun StockProgressItemPreview() {
    CashierTheme {
        CashBoxProgressItem(
            item = CashierProgressItem(R.string.outlet_selecting, true)
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun CashBoxItemPreview() {
    CashierTheme {
        CashBoxListItem(
            cashBox = CashBox(
                name = "testName",
                cashBoxId = "123",
                merchantId = "123",
                stockId = "123"
            ),
            selectedCashBox = CashBox(
                name = "testName",
                cashBoxId = "123",
                merchantId = "123",
                stockId = "123"
            ),
            onCashBoxClick = {}
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun SelectCashBoxScreenPreview() {
    CashierTheme {
        SelectCashBoxScreen(
            onBackButtonPressed = { },
            cashBoxProgressItems = listOf(
                CashierProgressItem(R.string.outlet_selecting, true),
                CashierProgressItem(R.string.outlet_checkout, true)
            ),

            cashBoxItems = listOf(
                CashBox(
                    name = "testName",
                    cashBoxId = "123",
                    merchantId = "123",
                    stockId = "123"
                ),
                CashBox(
                    name = "testName1",
                    cashBoxId = "1234",
                    merchantId = "1234",
                    stockId = "1234"
                )
            ),
            onRefresh = { },
            selectCashBox = {},
            onNextClick = { /*TODO*/ },
            isLoading = true,
            selectedCashBox = CashBox(
                name = "testName",
                cashBoxId = "123",
                merchantId = "123",
                stockId = "123"
            )
        )
    }
}