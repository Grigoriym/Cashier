package com.grappim.waybill.ui.product.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.button.BigActionButtonCompose
import com.grappim.uikit.theme.CashierTheme
import com.grappim.waybill.R
import com.grappim.waybill.ui.product.model.WaybillProductStates

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
            TextField(
                value = barcode,
                onValueChange = setBarcode,
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = stringResource(
                            id = R.string.title_barcode
                        )
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = productName,
                onValueChange = setProductName,
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = stringResource(
                            id = R.string.title_product_name
                        )
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = amount,
                onValueChange = setAmount,
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = stringResource(
                            id = R.string.title_amount
                        )
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = purchasePrice,
                onValueChange = setPurchasePrice,
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = stringResource(
                            id = R.string.title_purchase_price
                        )
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = sellingPrice,
                onValueChange = setSellingPrice,
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = stringResource(
                            id = R.string.title_selling_price
                        )
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White
                )
            )
        }
    }
}

@Preview
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