package com.grappim.feature.waybill.presentation.ui.details.ui.view

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.grappim.feature.waybill.domain.model.Waybill
import com.grappim.feature.waybill.domain.model.WaybillProduct
import com.grappim.uikit.R
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.CashierText
import com.grappim.uikit.compose.ItemProductCompose
import com.grappim.uikit.compose.button.BigActionButtonCompose
import com.grappim.uikit.compose.button.StandardFilledButton
import com.grappim.uikit.compose.text_field.CashierStandardTextField
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun WaybillDetailsScreen(
    waybill: Waybill,
    productsPagingItems: LazyPagingItems<WaybillProduct>,
    onSearchClick: () -> Unit,
    onScanClick: () -> Unit,
    onBackClick: () -> Unit,
    onActionClick: () -> Unit,
    onProductClick: (WaybillProduct) -> Unit,
    onDateClick: () -> Unit,
    onRefresh: () -> Unit,
    comment: String,
    setComment: (String) -> Unit,
    actualDate: String
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            BaseTopAppBar(
                toolbarTitle = stringResource(id = R.string.title_create_acceptance),
                backButtonAction = onBackClick
            )
        },
        bottomBar = {
            BottomBarSegment(
                price = waybill.totalCost.toString(),
                onActionButtonClick = onActionClick,
                waybillStatus = waybill.status
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            CashierText(
                text = stringResource(
                    R.string.title_acceptance_number,
                    waybill.number
                ),
                modifier = Modifier
                    .padding(
                        top = 32.dp,
                        start = 16.dp
                    )
            )

            TextFieldsSegment(
                onDateClick = onDateClick,
                date = actualDate,
                comment = comment,
                setComment = setComment
            )

            CashierText(
                text = stringResource(
                    R.string.title_goods_acceptance,
                    productsPagingItems.itemCount
                ),
                modifier = Modifier
                    .padding(
                        top = 24.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
            )

            ActionButtonsSegment(
                onSearchClick = onSearchClick,
                onScanClick = onScanClick
            )

            WaybillProductsList(
                productsPagingItems = productsPagingItems,
                onProductClick = onProductClick,
                onRefresh = onRefresh
            )
        }
    }
}

@Composable
private fun BottomBarSegment(
    price: String,
    onActionButtonClick: () -> Unit,
    waybillStatus: com.grappim.feature.waybill.domain.model.WaybillStatus
) {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp
                    )
            ) {
                CashierText(
                    modifier = Modifier
                        .weight(1f),
                    text = stringResource(id = R.string.title_price1),
                )

                CashierText(
                    modifier = Modifier
                        .weight(1f),
                    text = price,
                    color = CashierBlue,
                    textAlign = TextAlign.End
                )
            }
            val actionButtonText = when (waybillStatus) {
                com.grappim.feature.waybill.domain.model.WaybillStatus.ACTIVE -> {
                    R.string.action_rollback
                }

                com.grappim.feature.waybill.domain.model.WaybillStatus.DRAFT -> {
                    R.string.action_conduct
                }
            }
            BigActionButtonCompose(
                buttonText = stringResource(id = actionButtonText),
                onButtonClick = onActionButtonClick
            )
        }
    }
}

@Composable
private fun TextFieldsSegment(
    onDateClick: () -> Unit,
    date: String,
    comment: String,
    setComment: (String) -> Unit
) {
    val source = remember {
        MutableInteractionSource()
    }
    if (source.collectIsPressedAsState().value) {
        onDateClick()
    }

    Column {
        CashierStandardTextField(
            value = date,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            placeholderText = stringResource(id = R.string.title_actual_date),
            readOnly = true,
            interactionSource = source
        )

        CashierStandardTextField(
            value = comment,
            onValueChange = setComment,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 8.dp,
                    start = 16.dp,
                    end = 16.dp
                ),
            placeholderText = stringResource(id = R.string.title_comment),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        )
    }
}

@Composable
private fun ActionButtonsSegment(
    onSearchClick: () -> Unit,
    onScanClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
    ) {
        StandardFilledButton(
            onButtonClick = onSearchClick,
            imageVector = Icons.Filled.Search,
            modifier = Modifier
                .weight(1f)
                .padding(
                    end = 8.dp
                ),
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

@Composable
private fun WaybillProductsList(
    modifier: Modifier = Modifier,
    productsPagingItems: LazyPagingItems<WaybillProduct>,
    onProductClick: (WaybillProduct) -> Unit,
    onRefresh: () -> Unit
) {
    val pullRefreshState = rememberPullRefreshState(false, { onRefresh() })
    Box(
        modifier = Modifier
            .pullRefresh(pullRefreshState),
    ) {
        LazyColumn(
            modifier = modifier
        ) {
            items(productsPagingItems.itemCount) { index ->
                val item = productsPagingItems[index]
                item?.let {
                    ItemProductCompose(
                        waybillProduct = it,
                        onProductClick = onProductClick
                    )
                }
            }
        }
        PullRefreshIndicator(false, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
private fun TextFieldsSegmentPreview() {
    CashierTheme {
        TextFieldsSegment(
            onDateClick = {},
            date = "24.24.24",
            comment = "comment",
            setComment = {}
        )
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun TextFieldsSegmentNightPreview() {
    CashierTheme {
        TextFieldsSegment(
            onDateClick = {},
            date = "24.24.24",
            comment = "comment",
            setComment = {}
        )
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
private fun ActionButtonsSegmentPreview() {
    CashierTheme {
        ActionButtonsSegment(
            onScanClick = {},
            onSearchClick = {}
        )
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
private fun ActionButtonsSegmentPreviewNight() {
    CashierTheme {
        ActionButtonsSegment(
            onScanClick = {},
            onSearchClick = {}
        )
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
private fun WaybillDetailsScreenPreview() {
    CashierTheme {
        val products = flowOf(
            PagingData
                .empty<WaybillProduct>()
        ).collectAsLazyPagingItems()
        WaybillDetailsScreen(
            waybill = Waybill.empty(),
            productsPagingItems = products,
            onBackClick = {},
            onSearchClick = {},
            onScanClick = {},
            onActionClick = {},
            onProductClick = {},
            onDateClick = {},
            onRefresh = {},
            comment = "",
            setComment = {},
            actualDate = ""
        )
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun WaybillDetailsScreenNightPreview() {
    CashierTheme {
        val products = flowOf(
            PagingData
                .empty<WaybillProduct>()
        ).collectAsLazyPagingItems()
        WaybillDetailsScreen(
            waybill = Waybill.empty(),
            productsPagingItems = products,
            onBackClick = {},
            onSearchClick = {},
            onScanClick = {},
            onActionClick = {},
            onProductClick = {},
            onDateClick = {},
            onRefresh = {},
            comment = "",
            setComment = {},
            actualDate = ""
        )
    }
}