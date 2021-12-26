package com.grappim.menu.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierGreen
import com.grappim.uikit.theme.CashierTheme
import com.grappim.domain.model.menu.MenuItemType
import com.grappim.menu.R
import com.grappim.menu.model.MenuItemPm

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
            com.grappim.uikit.compose.BaseTopAppBar(
                toolbarTitle = stringResource(id = R.string.title_menu)
            ) {
                onBackButtonPressed()
            }
        }
    ) {
        MenuItemsSection(
            cashierName = cashierName,
            items = items,
            onItemClick = onItemClick
        )
    }
}

@Composable
private fun MenuItemsSection(
    cashierName: String,
    items: List<MenuItemPm>,
    onItemClick: (MenuItemPm) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
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
                    Card(
                        modifier = Modifier
                            .fillParentMaxWidth(),
                        elevation = 4.dp,
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            onItemClick(menuItem)
                        },
                        backgroundColor = Color.White,
                        indication = rememberRipple()
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
}

@Composable
private fun CashierNameSegment(
    cashierName: String
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = cashierName,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 18.dp),
            color = Color.Black
        )
        Spacer(modifier = Modifier.width(13.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_dot),
            tint = CashierGreen,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun MenuItemRow(
    modifier: Modifier = Modifier,
    item: MenuItemPm
) {
    Row(
        modifier = modifier
            .padding(all = 24.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = item.image),
            contentDescription = "Menu item image"
        )
        Spacer(modifier = Modifier.width(40.dp))
        Text(
            text = stringResource(id = item.text),
            modifier = Modifier
                .align(Alignment.CenterVertically),
            fontSize = 22.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            painter = painterResource(
                id = R.drawable.ic_keyboard_arrow_right
            ),
            tint = CashierBlue,
            contentDescription = "",
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
    CashierTheme() {
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
                )
            ),
            onItemClick = {},
            onBackButtonPressed = {}
        )
    }
}