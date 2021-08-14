package com.grappim.cashier.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.grappim.cashier.R
import com.grappim.cashier.compose.BaseTopAppBar
import com.grappim.cashier.core.storage.GeneralStorage
import com.grappim.cashier.ui.theme.CashierBlue
import com.grappim.cashier.ui.theme.CashierGreen
import com.grappim.cashier.ui.theme.CashierTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MenuFragment : Fragment(), MenuItemClickListener {

    @Inject
    lateinit var generalStorage: GeneralStorage

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            CashierTheme() {
                MenuScreen(
                    cashierName = generalStorage.getCashierName(),
                    onItemClick = { menuItem: MenuItem ->
                        onItemClick(menuItem)
                    },
                    onBackButtonPressed = {
                        findNavController().popBackStack()
                    }
                )
            }
        }
    }

    override fun onItemClick(item: MenuItem) {
        when (item.type) {
            MenuItemType.ACCEPTANCE -> {
                findNavController().navigate(R.id.action_menuFragment_to_acceptanceFragment)
            }
            MenuItemType.PRODUCTS -> {
                findNavController().navigate(R.id.action_menuFragment_to_productsFragment)
            }
            MenuItemType.SALES -> {
                findNavController().navigate(R.id.action_menuFragment_to_salesFragment)
            }
        }
    }

}

@Composable
private fun MenuScreen(
    cashierName: String,
    viewModel: MenuViewModel = viewModel(),
    onItemClick: (MenuItem) -> Unit,
    onBackButtonPressed: () -> Unit
) {
    val items = viewModel.menuItems.collectAsState()
    Scaffold(
        modifier = Modifier,
        topBar = {
            BaseTopAppBar(
                toolbarTitle = stringResource(id = R.string.title_menu),
                backButtonTitle = stringResource(id = R.string.action_back)
            ) {
                onBackButtonPressed()
            }
        }
    ) {
        MenuItemsSection(
            cashierName = cashierName,
            items = items.value,
            onItemClick = onItemClick
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MenuItemsSection(
    cashierName: String,
    items: List<MenuItem>,
    onItemClick: (MenuItem) -> Unit
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
    item: MenuItem
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
        item = MenuItem(
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
            viewModel(),
            onItemClick = {},
            onBackButtonPressed = {}
        )
    }
}