package com.grappim.cashier.ui.waybill.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.grappim.cashier.R
import com.grappim.cashier.compose.BaseTopAppBar
import com.grappim.cashier.compose.BigActionButtonCompose
import com.grappim.cashier.compose.OutlinedTextFieldCompose
import com.grappim.cashier.core.extensions.bigDecimalOne
import com.grappim.cashier.core.extensions.getErrorMessage
import com.grappim.cashier.core.extensions.getOffsetDateTimeFromString
import com.grappim.cashier.core.extensions.showToast
import com.grappim.cashier.core.functional.Resource
import com.grappim.cashier.core.utils.DateTimeUtils
import com.grappim.cashier.core.view.CashierLoaderDialog
import com.grappim.cashier.di.modules.DecimalFormatSimple
import com.grappim.cashier.domain.waybill.Waybill
import com.grappim.cashier.ui.theme.*
import com.grappim.cashier.ui.waybill.WaybillSharedViewModel
import com.grappim.cashier.ui.waybill.WaybillStatus
import com.grappim.cashier.ui.waybill.WaybillType
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
import javax.inject.Inject

@AndroidEntryPoint
class WaybillListFragment : Fragment(), WaybillListClickListener {

    @Inject
    @DecimalFormatSimple
    lateinit var dfSimple: DecimalFormat

    private val viewModel: WaybillListViewModel by viewModels()
    private val sharedViewModel by navGraphViewModels<WaybillSharedViewModel>(R.id.nav_graph_waybill)

    private val loader: CashierLoaderDialog by lazy {
        CashierLoaderDialog(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme {
                WaybillListScreen(
                    onBackButtonPressed = {
                        findNavController().popBackStack()
                    },
                    onCreateAcceptanceClick = {
                        viewModel.createWaybill()
                    },
                    onWaybillClick = {
                        onWaybillClick(it)
                    },
                    loaderDialog = loader,
                    showError = {
                        showToast(getErrorMessage(it))
                    }
                )
            }
        }
    }

    override fun onWaybillClick(waybill: Waybill) {
        sharedViewModel.setCurrentWaybill(waybill)
        findNavController()
            .navigate(
                R.id.action_waybill_to_waybillDetails
            )
    }
}

private val dtfDateTime = DateTimeUtils.getDateTimePatternStandard()

@Composable
private fun WaybillListScreen(
    viewModel: WaybillListViewModel = viewModel(),
    onBackButtonPressed: () -> Unit,
    onCreateAcceptanceClick: () -> Unit,
    onWaybillClick: (Waybill) -> Unit,
    loaderDialog: CashierLoaderDialog,
    showError: (Throwable) -> Unit
) {
    val (text, setText) = remember {
        mutableStateOf("")
    }
    Scaffold(
        modifier = Modifier,
        topBar = {
            BaseTopAppBar(
                toolbarTitle = stringResource(id = R.string.title_acceptance),
                backButtonTitle = stringResource(id = R.string.title_menu)
            ) {
                onBackButtonPressed()
            }
        },
        bottomBar = {
            val createState = viewModel.waybill.observeAsState()
            loaderDialog.showOrHide(createState.value is Resource.Loading)

            val waybillValue = createState.value
            when (waybillValue) {
                is Resource.Success -> {
                    val waybill = waybillValue.data
                    onWaybillClick(waybill)
                }
                is Resource.Error -> {
                    showError(waybillValue.exception)
                }
            }
            BigActionButtonCompose(
                modifier = Modifier,
                onCreateAcceptanceClick = onCreateAcceptanceClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            OutlinedTextFieldCompose(
                modifier = Modifier
                    .padding(
                        top = 24.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                text = text,
                onTextChange = setText
            )
            WaybillListSegment(
                viewModel = viewModel,
                onWaybillClick = onWaybillClick,
                modifier = Modifier.padding(top = 24.dp)
            )
        }
    }
}

@Composable
private fun WaybillListSegment(
    viewModel: WaybillListViewModel,
    onWaybillClick: (Waybill) -> Unit,
    modifier: Modifier = Modifier
) {
    val lazyPagingItems = viewModel.acceptances.collectAsLazyPagingItems()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = { viewModel.refresh() }
    ) {
        LazyColumn(
            modifier = modifier
        ) {
            items(lazyPagingItems) { item ->
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
                }
            }
        }
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
                color = Color.White
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

        Text(
            text = item.item.number,
            color = Color.Black,
            fontSize = 16.sp,
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

        Text(
            text = dtfDateTime.format(
                item.item.updatedOn.getOffsetDateTimeFromString()
            ),
            color = CashierGray,
            fontSize = 14.sp,
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
        Text(
            text = stringResource(
                id = R.string.title_price_with_currency,
                item.item.totalCost.toString()
            ),
            fontSize = 16.sp,
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
            WaybillStatus.ACTIVE -> {
                CashierGreen
            }
            WaybillStatus.DRAFT -> {
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
            Text(
                text = item.item.status.name,
                fontSize = 14.sp,
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
        Icon(
            painter = painterResource(id = R.drawable.ic_calendar_today_24),
            contentDescription = "",
            tint = CashierGray,
            modifier = Modifier
                .padding(start = 18.dp)
        )
        Text(
            text = header.text,
            modifier = Modifier
                .padding(start = 12.dp),
            color = CashierGray,
            fontSize = 16.sp
        )
    }
}

@Composable
@Preview
private fun WaybillListScreenPreview() {
    CashierTheme {
//        WaybillListScreen(
//            onBackButtonPressed = { },
//            onCreateAcceptanceClick = {},
//            viewModel = viewModel(),
//            onWaybillClick = {},
//            loaderDialog = CashierLoaderDialog()
//        )
    }
}

@Composable
@Preview
private fun WaybillItemSegmentPreview() {
    CashierTheme {
        WaybillItemSegment(
            item = PagingDataModel.Item(
                item = Waybill(
                    id = 1,
                    createdOn = "",
                    merchantId = "",
                    number = "2312312",
                    status = WaybillStatus.ACTIVE,
                    stockId = "",
                    totalCost = bigDecimalOne(),
                    type = WaybillType.INWAYBILL,
                    updatedOn = "23.12.23.20123",
                    reservedTime = "",
                    comment = ""
                )
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