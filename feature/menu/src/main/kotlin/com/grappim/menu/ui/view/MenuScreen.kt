package com.grappim.menu.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.domain.model.menu.MenuItemType
import com.grappim.menu.model.MenuItemPm
import com.grappim.uikit.R
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.CashierIcon
import com.grappim.uikit.compose.CashierImage
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierGreen
import com.grappim.uikit.theme.CashierTheme

@Composable
fun MenuScreen(
    cashierName: String,
    items: List<MenuItemPm>,
    onItemClick: (MenuItemPm) -> Unit,
    onBackButtonPressed: () -> Unit
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            BaseTopAppBar(
                toolbarTitle = stringResource(id = R.string.title_menu)
            ) {
                onBackButtonPressed()
            }
        }
    ) {
        MenuItemsSection(
            modifier = Modifier.padding(it),
            cashierName = cashierName,
            items = items,
            onItemClick = onItemClick
        )
    }
}

@Composable
private fun MenuItemsSection(
    modifier: Modifier = Modifier,
    cashierName: String,
    items: List<MenuItemPm>,
    onItemClick: (MenuItemPm) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        CashierNameSegment(cashierName = cashierName)
        LazyColumn(
            modifier = Modifier
                .padding(top = 24.dp)
                .weight(1f),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 10.dp,
                end = 16.dp,
                bottom = 10.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(items) { menuItem ->
                rememberRipple()
                Card(
                    onClick = {
                        onItemClick(menuItem)
                    },
                    modifier = Modifier
                        .fillParentMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    elevation = 4.dp
                ) {
                    MenuItemRow(
                        item = menuItem,
                        modifier = Modifier
                            .fillParentMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun CashierNameSegment(cashierName: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = cashierName,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 18.dp)
        )
        Spacer(modifier = Modifier.width(13.dp))
        CashierIcon(
            imageVector = Icons.Filled.Circle,
            tint = CashierGreen,
            modifier = Modifier
                .size(12.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun MenuItemRow(modifier: Modifier = Modifier, item: MenuItemPm) {
    Row(
        modifier = modifier
            .padding(all = 24.dp)
            .fillMaxWidth()
    ) {
        if (item.image != null) {
            CashierImage(
                painter = painterResource(id = item.image)
            )
        } else if (item.imageVector != null) {
            CashierImage(
                imageVector = item.imageVector,
                modifier = Modifier
                    .size(44.dp),
                colorFilter = ColorFilter.tint(CashierBlue)
            )
        }
        Spacer(modifier = Modifier.width(40.dp))
        Text(
            text = stringResource(id = item.text),
            modifier = Modifier
                .align(Alignment.CenterVertically),
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        CashierIcon(
            imageVector = Icons.Filled.ChevronRight,
            tint = CashierBlue,
            modifier = Modifier
                .padding(end = 34.dp)
                .align(Alignment.CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MenuItemRowPreview() {
    MenuItemRow(
        item = MenuItemPm(
            type = MenuItemType.SALES,
            text = R.string.title_sales,
            image = R.drawable.ic_cash_register
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CashierNameSegmentPreview() {
    CashierNameSegment(cashierName = "Test name")
}

@Preview(showBackground = true)
@Composable
private fun MenuScreenPreview() {
    CashierTheme {
        MenuScreen(
            cashierName = "Cashier name",
            items = listOf(
                MenuItemPm(
                    type = MenuItemType.SALES,
                    text = R.string.title_sales,
                    image = R.drawable.ic_cash_register
                ),
                MenuItemPm(
                    type = MenuItemType.PRODUCTS,
                    text = R.string.title_products,
                    image = R.drawable.ic_store
                ),
                MenuItemPm(
                    type = MenuItemType.ACCEPTANCE,
                    text = R.string.title_acceptance,
                    image = R.drawable.ic_store_acceptance
                ),
                MenuItemPm(
                    type = MenuItemType.SETTINGS,
                    text = R.string.title_settings,
                    imageVector = Icons.Filled.Settings
                )
            ),
            onItemClick = {},
            onBackButtonPressed = {}
        )
    }
}
