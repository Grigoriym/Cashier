package com.grappim.cashier.ui.selectinfo.cashbox

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.grappim.cashier.R
import com.grappim.cashier.compose.BaseTopAppBar
import com.grappim.cashier.compose.BigActionButtonCompose
import com.grappim.cashier.domain.cashbox.CashBox
import com.grappim.cashier.ui.selectinfo.stock.StockProgressItem
import com.grappim.cashier.ui.theme.CashierGreen
import com.grappim.cashier.ui.theme.CashierLightGray
import com.grappim.cashier.ui.theme.CashierTheme

@Composable
fun SelectCashBoxScreen(
    onBackButtonPressed: () -> Unit,
    cashBoxProgressItems: List<StockProgressItem>,
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
                toolbarTitle = "",
                backButtonTitle = stringResource(id = R.string.action_back)
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Text(
                text = stringResource(id = R.string.title_checkout_selection),
                color = Color.Black,
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
                    StockProgressItem(
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
                Icon(
                    painter = painterResource(id = R.drawable.ic_check_circle_green),
                    contentDescription = "",
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

@Preview
@Composable
private fun StockProgressItemPreview() {
    CashierTheme {
        StockProgressItem(
            item = StockProgressItem(R.string.outlet_selecting, true)
        )
    }
}

@Preview
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

@Preview
@Composable
private fun SelectCashBoxScreenPreview() {
    CashierTheme {
        SelectCashBoxScreen(
            onBackButtonPressed = { },
            cashBoxProgressItems = listOf(
                StockProgressItem(R.string.outlet_selecting, true),
                StockProgressItem(R.string.outlet_checkout, true)
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