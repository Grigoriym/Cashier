package com.grappim.stock.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.domain.model.outlet.Stock
import com.grappim.stock.model.StockProgressItem
import com.grappim.uikit.R
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.CashierIcon
import com.grappim.uikit.compose.button.BigActionButtonCompose
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierGray
import com.grappim.uikit.theme.CashierGreen
import com.grappim.uikit.theme.CashierLightGray
import com.grappim.uikit.theme.CashierTheme

@Composable
fun SelectStockScreen(
    onBackButtonPressed: () -> Unit,
    stockProgressItems: List<StockProgressItem>,
    stockItems: List<Stock>,
    onRefresh: () -> Unit,
    selectStock: (Stock) -> Unit,
    selectedStock: Stock? = null,
    onNextClick: () -> Unit,
    isLoading: Boolean
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
                isEnabled = selectedStock != null
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Text(
                text = stringResource(id = R.string.outlet_selecting),
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
                items(stockProgressItems) { item ->
                    StockProgressItem(
                        item = item,
                        modifier = Modifier
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            StockListSegment(
                stockItems = stockItems,
                isRefreshing = isLoading,
                onRefresh = { onRefresh() },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    ),
                onStockClick = selectStock,
                selectedStock = selectedStock
            )
        }
    }
}

@Composable
private fun StockListSegment(
    stockItems: List<Stock>,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    onStockClick: (Stock) -> Unit,
    selectedStock: Stock?
) {
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { onRefresh() })
    Box(modifier = Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn(modifier = modifier) {
            items(stockItems) { item ->
                StockListItem(
                    stock = item,
                    selectedStock = selectedStock,
                    onStockClick = onStockClick
                )
                Divider(
                    thickness = 1.dp,
                    color = CashierLightGray
                )
            }
        }
        PullRefreshIndicator(isRefreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}

@Composable
private fun StockListItem(stock: Stock, selectedStock: Stock?, onStockClick: (Stock) -> Unit) {
    val isItemSelected = stock.name == selectedStock?.name &&
        stock.merchantId == selectedStock.merchantId
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onStockClick(stock)
            }
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 10.dp,
                bottom = 10.dp
            )

    ) {
        Text(
            text = stock.name,
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
fun StockProgressItem(item: StockProgressItem, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
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
private fun StockListItemPreview() {
    CashierTheme {
        StockListItem(
            stock = Stock(
                "Point of sale",
                "me",
                "stockId"
            ),
            onStockClick = {},
            selectedStock = Stock(
                "Point of sale",
                "me",
                "stockId"
            )
        )
    }
}

@Preview
@Composable
private fun SelectStockScreenPreview() {
    CashierTheme {
        SelectStockScreen(
            onBackButtonPressed = { },
            stockProgressItems = listOf(
                StockProgressItem(R.string.outlet_selecting, true),
                StockProgressItem(R.string.outlet_checkout, false)
            ),
            stockItems = listOf(
                Stock(
                    "Point of sale",
                    "me",
                    "stockId"
                ),
                Stock(
                    "Point of sale1",
                    "2me",
                    "stock2Id"
                )
            ),
            onRefresh = {},
            selectedStock = Stock(
                "Point of sale",
                "me",
                "stockId"
            ),
            selectStock = {},
            onNextClick = {},
            isLoading = true
        )
    }
}
