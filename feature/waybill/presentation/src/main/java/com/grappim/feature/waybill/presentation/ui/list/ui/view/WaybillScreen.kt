package com.grappim.feature.waybill.presentation.ui.list.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.grappim.feature.waybill.domain.model.Waybill
import com.grappim.feature.waybill.presentation.model.PagingDataModel
import com.grappim.uikit.R
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.CashierIcon
import com.grappim.uikit.compose.CashierText
import com.grappim.uikit.compose.CashierTextBody1
import com.grappim.uikit.compose.CashierTextGray
import com.grappim.uikit.compose.button.BigActionButtonCompose
import com.grappim.uikit.compose.textfield.CashierSearchTextField
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierGray
import com.grappim.uikit.theme.CashierGreen
import com.grappim.uikit.theme.CashierLightGray
import com.grappim.uikit.theme.CashierTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun WaybillListScreen(
    onBackButtonPressed: () -> Unit,
    onCreateAcceptanceClick: () -> Unit,
    onWaybillClick: (Waybill) -> Unit,
    onRefresh: () -> Unit,
    lazyPagingItems: LazyPagingItems<PagingDataModel<Waybill>>,
    isRefreshing: Boolean,
    searchText: String,
    setSearchText: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            BaseTopAppBar(
                toolbarTitle = stringResource(id = R.string.title_acceptance),
                backButtonAction = onBackButtonPressed
            )
        },
        bottomBar = {
            BigActionButtonCompose(
                buttonText = stringResource(id = R.string.action_create_acceptance),
                modifier = Modifier,
                onButtonClick = onCreateAcceptanceClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
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
            WaybillListSegment(
                onWaybillClick = onWaybillClick,
                modifier = Modifier.padding(top = 24.dp),
                onRefresh = onRefresh,
                lazyPagingItems = lazyPagingItems,
                isRefreshing = isRefreshing
            )
        }
    }
}

@Composable
private fun WaybillListSegment(
    modifier: Modifier = Modifier,
    onWaybillClick: (Waybill) -> Unit,
    onRefresh: () -> Unit,
    lazyPagingItems: LazyPagingItems<PagingDataModel<Waybill>>,
    isRefreshing: Boolean
) {
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { onRefresh() })
    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState),
    ) {
        LazyColumn(
            modifier = modifier
        ) {
            items(lazyPagingItems.itemCount) { index ->
                val item = lazyPagingItems[index]
                when (item) {
                    is PagingDataModel.Separator -> {
                        WaybillListHeaderSegment(header = item)
                    }

                    is PagingDataModel.Item -> {
                        WaybillItemSegment(
                            item = item,
                            onWaybillClick = onWaybillClick
                        )
                    }

                    else -> {

                    }
                }
            }
        }
        PullRefreshIndicator(isRefreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}

@Composable
private fun WaybillItemSegment(
    item: PagingDataModel.Item<Waybill>,
    onWaybillClick: (Waybill) -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.background
            )
            .clickable {
                onWaybillClick.invoke(item.item)
            }
    ) {
        val (
            textNumber,
            textDate,
            textTotalCost,
            textStatus,
            divider
        ) = createRefs()

        CashierText(
            text = item.item.number,
            modifier = Modifier
                .constrainAs(textNumber) {
                    top.linkTo(
                        anchor = parent.top,
                        margin = 10.dp
                    )
                    start.linkTo(
                        anchor = parent.start,
                        margin = 20.dp
                    )
                }
        )

        CashierTextBody1(
            text = item.item.updateOnToDemonstrate,
            color = CashierGray,
            modifier = Modifier
                .constrainAs(textDate) {
                    start.linkTo(
                        anchor = textNumber.start
                    )
                    top.linkTo(
                        anchor = textNumber.bottom,
                        margin = 9.dp
                    )
                    bottom.linkTo(
                        anchor = parent.bottom,
                        margin = 9.dp
                    )
                }
        )
        CashierText(
            text = stringResource(
                id = R.string.title_price_with_currency,
                item.item.totalCost.toString()
            ),
            color = CashierBlue,
            modifier = Modifier
                .constrainAs(textTotalCost) {
                    start.linkTo(
                        anchor = textDate.end,
                        margin = 9.dp
                    )
                    bottom.linkTo(
                        anchor = textDate.bottom
                    )
                }
        )

        val statusColor = when (item.item.status) {
            com.grappim.feature.waybill.domain.model.WaybillStatus.ACTIVE -> {
                CashierGreen
            }

            com.grappim.feature.waybill.domain.model.WaybillStatus.DRAFT -> {
                CashierBlue
            }
        }
        Card(
            shape = RoundedCornerShape(15.dp),
            backgroundColor = statusColor,
            modifier = Modifier
                .constrainAs(textStatus) {
                    end.linkTo(
                        anchor = parent.end,
                        margin = 18.dp
                    )
                    top.linkTo(
                        anchor = textNumber.top
                    )
                }
        ) {
            CashierTextBody1(
                text = item.item.status.name,
                color = Color.White,
                modifier = Modifier
                    .padding(
                        start = 18.dp,
                        top = 2.dp,
                        end = 18.dp,
                        bottom = 2.dp
                    )
            )
        }

        Divider(
            color = CashierLightGray,
            thickness = 1.dp,
            modifier = Modifier
                .constrainAs(divider) {
                    start.linkTo(
                        anchor = textNumber.start
                    )
                    end.linkTo(
                        anchor = textStatus.end
                    )
                    bottom.linkTo(
                        anchor = parent.bottom
                    )
                }
        )
    }
}

@Composable
private fun WaybillListHeaderSegment(
    header: PagingDataModel.Separator
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 10.dp,
                bottom = 10.dp
            )
    ) {
        CashierIcon(
            imageVector = Icons.Filled.CalendarToday,
            tint = CashierGray,
            modifier = Modifier
                .padding(start = 18.dp)
        )
        CashierTextGray(
            text = header.text,
            modifier = Modifier
                .padding(start = 12.dp)
        )
    }
}

@Composable
@Preview
private fun WaybillListScreenPreview() {
    CashierTheme {
        val items = flowOf(
            PagingData.empty<PagingDataModel<Waybill>>()
        ).collectAsLazyPagingItems()
        WaybillListScreen(
            onBackButtonPressed = { },
            onCreateAcceptanceClick = {},
            onWaybillClick = {},
            onRefresh = {},
            lazyPagingItems = items,
            isRefreshing = false,
            searchText = "searccc",
            setSearchText = {}
        )
    }
}

@Composable
@Preview
private fun WaybillItemSegmentPreview() {
    CashierTheme {
        WaybillItemSegment(
            item = PagingDataModel.Item(
                item = Waybill.empty()
            ),
            onWaybillClick = {}
        )
    }
}

@Composable
@Preview
private fun WaybillListHeaderSegmentPreview() {
    CashierTheme {
        WaybillListHeaderSegment(
            PagingDataModel.Separator("24.03.2021")
        )
    }
}
