package com.grappim.feature.waybill.presentation.ui.product.ui.view

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grappim.feature.waybill.presentation.R
import com.grappim.feature.waybill.presentation.ui.product.model.WaybillProductStates
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.button.BigActionButtonCompose
import com.grappim.uikit.compose.text_field.CashierStandardTextField
import com.grappim.uikit.theme.CashierTheme

@Composable
fun WaybillProductScreen(
    waybillProductStates: WaybillProductStates,
    setBarcode: (String) -> Unit,
    setProductName: (String) -> Unit,
    setAmount: (String) -> Unit,
    setPurchasePrice: (String) -> Unit,
    setSellingPrice: (String) -> Unit,
    onBackClick: () -> Unit,
    onActionClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            BaseTopAppBar(
                toolbarTitle = stringResource(id = R.string.acceptance_product),
                backButtonAction = onBackClick
            )
        },
        bottomBar = {
            BigActionButtonCompose(
                buttonText = stringResource(id = R.string.action_add_product),
                onButtonClick = onActionClick
            )
        }
    ) { innerPadding ->
        val barcode: String
        val productName: String
        val amount: String
        val purchasePrice: String
        val sellingPrice: String

        when (waybillProductStates) {
            is WaybillProductStates.EmptyState -> {
                barcode = ""
                productName = ""
                amount = ""
                purchasePrice = ""
                sellingPrice = ""
            }
            is WaybillProductStates.WaybillProductState -> {
                barcode = waybillProductStates.barcode
                productName = waybillProductStates.name
                amount = waybillProductStates.amountToShow
                purchasePrice = waybillProductStates.purchasePriceToShow
                sellingPrice = waybillProductStates.sellingPriceToShow
            }
        }

        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp
                )
        ) {
            CashierStandardTextField(
                value = barcode,
                onValueChange = setBarcode,
                modifier = Modifier
                    .fillMaxWidth(),
                placeholderText = stringResource(
                    id = R.string.title_barcode
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                )
            )
            CashierStandardTextField(
                value = productName,
                onValueChange = setProductName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 16.dp
                    ),
                placeholderText = stringResource(
                    id = R.string.title_product_name
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                )
            )
            CashierStandardTextField(
                value = amount,
                onValueChange = setAmount,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 16.dp
                    ),
                placeholderText = stringResource(
                    id = R.string.title_amount
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            CashierStandardTextField(
                value = purchasePrice,
                onValueChange = setPurchasePrice,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 16.dp
                    ),
                placeholderText = stringResource(
                    id = R.string.title_purchase_price
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
            CashierStandardTextField(
                value = sellingPrice,
                onValueChange = setSellingPrice,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 16.dp
                    ),
                placeholderText = stringResource(
                    id = R.string.title_selling_price
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )
        }
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
private fun WaybillProductScreenPreview() {
    CashierTheme {
        WaybillProductScreen(
            onBackClick = {},
            onActionClick = {},
            waybillProductStates = WaybillProductStates.EmptyState,
            setBarcode = {},
            setProductName = {},
            setAmount = {},
            setPurchasePrice = {},
            setSellingPrice = {}
        )
    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
private fun WaybillProductScreenNightPreview() {
    CashierTheme {
        WaybillProductScreen(
            onBackClick = {},
            onActionClick = {},
            waybillProductStates = WaybillProductStates.EmptyState,
            setBarcode = {},
            setProductName = {},
            setAmount = {},
            setPurchasePrice = {},
            setSellingPrice = {}
        )
    }
}