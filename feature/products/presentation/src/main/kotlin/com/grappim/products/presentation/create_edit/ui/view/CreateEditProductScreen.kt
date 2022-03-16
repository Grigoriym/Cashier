package com.grappim.products.presentation.create_edit.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.grappim.domain.model.base.ProductUnit
import com.grappim.product_category.domain.model.ProductCategory
import com.grappim.products.presentation.R
import com.grappim.products.presentation.model.CreateEditFlow
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.CounterComposable
import com.grappim.uikit.compose.button.BigActionButtonCompose
import com.grappim.uikit.compose.button.StandardFilledButton
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierGray
import com.grappim.uikit.theme.CashierLightGray
import com.grappim.uikit.theme.CashierTheme

@Composable
fun CreateEditProductScreen(
    onBackPressed: () -> Unit,
    onCreateProductClick: () -> Unit,
    productName: String,
    setProductName: (String) -> Unit,
    units: List<ProductUnit>,
    selectedUnit: ProductUnit,
    onUnitChanged: (ProductUnit) -> Unit,
    onMinusClick: () -> Unit,
    onPlusClick: () -> Unit,
    quantityAndUnitText: String,
    purchasePrice: String,
    setPurchasePrice: (String) -> Unit,
    extraPrice: String,
    setExtraPrice: (String) -> Unit,
    sellingPrice: String,
    setSellingPrice: (String) -> Unit,
    barcode: String,
    setBarcode: (String) -> Unit,
    dropDownExpanded: Boolean,
    onDismissDropDown: () -> Unit,
    onDropDownClick: () -> Unit,
    categoryItems: List<ProductCategory>,
    onCategoryClick: (ProductCategory) -> Unit,
    createEditFlow: CreateEditFlow,
    selectedCategory: ProductCategory? = null,
    onScanClick: () -> Unit
) {
    val title = when (createEditFlow) {
        CreateEditFlow.EDIT -> {
            stringResource(id = R.string.title_edit_product)
        }
        CreateEditFlow.CREATE -> {
            stringResource(id = R.string.title_create_product)
        }
    }

    Scaffold(
        modifier = Modifier,
        topBar = {
            BaseTopAppBar(
                toolbarTitle = title
            ) {
                onBackPressed()
            }
        },
        bottomBar = {
            BigActionButtonCompose(
                buttonText = title,
                modifier = Modifier,
                onButtonClick = onCreateProductClick
            )
        }
    ) {
        CreateEditProductScreenMainSegment(
            modifier = Modifier.padding(it),
            productName = productName,
            setProductName = setProductName,
            units = units,
            selectedUnit = selectedUnit,
            onUnitChanged = onUnitChanged,
            onMinusClick = onMinusClick,
            onPlusClick = onPlusClick,
            quantityAndUnitText = quantityAndUnitText,
            purchasePrice = purchasePrice,
            setPurchasePrice = setPurchasePrice,
            extraPrice = extraPrice,
            setExtraPrice = setExtraPrice,
            sellingPrice = sellingPrice,
            setSellingPrice = setSellingPrice,
            barcode = barcode,
            setBarcode = setBarcode,
            dropDownExpanded = dropDownExpanded,
            onDismissDropDown = onDismissDropDown,
            onDropDownClick = onDropDownClick,
            categoryItems = categoryItems,
            onCategoryClick = onCategoryClick,
            selectedCategory = selectedCategory,
            onScanClick = onScanClick
        )
    }
}

@Composable
private fun CreateEditProductScreenMainSegment(
    modifier: Modifier = Modifier,
    productName: String,
    setProductName: (String) -> Unit,
    units: List<ProductUnit>,
    selectedUnit: ProductUnit,
    onUnitChanged: (ProductUnit) -> Unit,
    onMinusClick: () -> Unit,
    onPlusClick: () -> Unit,
    quantityAndUnitText: String,
    purchasePrice: String,
    setPurchasePrice: (String) -> Unit,
    extraPrice: String,
    setExtraPrice: (String) -> Unit,
    sellingPrice: String,
    setSellingPrice: (String) -> Unit,
    barcode: String,
    setBarcode: (String) -> Unit,
    dropDownExpanded: Boolean,
    onDismissDropDown: () -> Unit,
    onDropDownClick: () -> Unit,
    categoryItems: List<ProductCategory>,
    onCategoryClick: (ProductCategory) -> Unit,
    selectedCategory: ProductCategory?,
    onScanClick: () -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp
            ),
        state = listState
    ) {
        item {
            val focusManager = LocalFocusManager.current
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
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = CashierBlue,
                    unfocusedIndicatorColor = CashierLightGray
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
        }

        item {
            ProductUnitSegment(
                units = units,
                selectedUnit = selectedUnit,
                onUnitChanged = onUnitChanged
            )
        }
        item {
            QuantitySegment(
                onMinusClick = onMinusClick,
                onPlusClick = onPlusClick,
                quantityAndUnitText = quantityAndUnitText
            )
        }

        item {
            CategoryDropDownSegment(
                dropDownExpanded = dropDownExpanded,
                onDismissDropDown = onDismissDropDown,
                categoryItems = categoryItems,
                onCategoryClick = onCategoryClick,
                selectedCategory = selectedCategory,
                onDropDownClick = onDropDownClick
            )
        }

        item {
            val focusManager = LocalFocusManager.current

            TextField(
                value = barcode,
                onValueChange = setBarcode,
                modifier = Modifier
                    .padding(
                        top = 16.dp
                    )
                    .fillMaxWidth(),
                placeholder = {
                    Text(
                        text = stringResource(
                            id = R.string.title_barcode
                        )
                    )
                },
                label = {
                    Text(
                        text = stringResource(
                            id = R.string.title_barcode
                        )
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = CashierBlue,
                    unfocusedIndicatorColor = CashierLightGray
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
        }

        item {
            StandardFilledButton(
                modifier = Modifier
                    .padding(
                        top = 16.dp
                    )
                    .width(115.dp),
                onButtonClick = {
                    onScanClick()
                },
                iconDrawable = R.drawable.ic_barcode,
                backgroundColor = Color.White,
                iconTint = CashierBlue
            )
        }

        item {
            PricesSegment(
                purchasePrice = purchasePrice,
                setPurchasePrice = setPurchasePrice,
                extraPrice = extraPrice,
                setExtraPrice = setExtraPrice,
                sellingPrice = sellingPrice,
                setSellingPrice = setSellingPrice
            )
        }
    }
}

@Composable
private fun CategoryDropDownSegment(
    dropDownExpanded: Boolean,
    onDismissDropDown: () -> Unit,
    categoryItems: List<ProductCategory>,
    onCategoryClick: (ProductCategory) -> Unit,
    selectedCategory: ProductCategory?,
    onDropDownClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(
                top = 16.dp
            )
            .fillMaxWidth()
            .drawBehind {
                val strokeWidth = 3f
                val y = size.height - strokeWidth / 2
                drawLine(
                    color = CashierLightGray,
                    start = Offset(0f, y),
                    end = Offset(size.width, y),
                    strokeWidth = strokeWidth
                )
            }
            .clickable {
                onDropDownClick()
            }
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    all = 16.dp
                )
        ) {
            val (
                name,
                arrow
            ) = createRefs()

            val text = selectedCategory?.name ?: stringResource(id = R.string.title_category)
            val color = if (selectedCategory != null) {
                Color.Black
            } else {
                CashierGray
            }

            Text(
                text = text,
                fontSize = 14.sp,
                color = color,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(name) {
                        top.linkTo(
                            anchor = parent.top
                        )
                        start.linkTo(
                            anchor = parent.start
                        )
                        bottom.linkTo(
                            anchor = parent.bottom
                        )
                        end.linkTo(
                            anchor = arrow.start
                        )
                        width = Dimension.fillToConstraints
                    }
            )

            Icon(
                imageVector = Icons.Filled.ExpandMore,
                contentDescription = null,
                tint = CashierBlue,
                modifier = Modifier
                    .size(20.dp)
                    .constrainAs(arrow) {
                        top.linkTo(
                            anchor = parent.top
                        )
                        end.linkTo(
                            anchor = parent.end
                        )
                        bottom.linkTo(
                            anchor = parent.bottom
                        )
                    }
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.TopEnd)
            ) {
                DropdownMenu(
                    expanded = dropDownExpanded,
                    onDismissRequest = onDismissDropDown,
                    modifier = Modifier
                        .background(
                            Color.White
                        )
                ) {
                    categoryItems.forEachIndexed { index, category ->
                        DropdownMenuItem(
                            onClick = {
                                onCategoryClick(category)
                            }

                        ) {
                            Text(text = category.name)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PricesSegment(
    purchasePrice: String,
    setPurchasePrice: (String) -> Unit,
    extraPrice: String,
    setExtraPrice: (String) -> Unit,
    sellingPrice: String,
    setSellingPrice: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column {
        Text(
            modifier = Modifier
                .padding(
                    top = 24.dp
                ),
            text = stringResource(id = R.string.title_price),
            color = Color.Black,
            fontSize = 16.sp
        )

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
            label = {
                Text(
                    text = stringResource(
                        id = R.string.title_purchase_price
                    )
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = CashierBlue,
                unfocusedIndicatorColor = CashierLightGray
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )

        TextField(
            value = extraPrice,
            onValueChange = setExtraPrice,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = stringResource(
                        id = R.string.title_extra_price
                    )
                )
            },
            label = {
                Text(
                    text = stringResource(
                        id = R.string.title_extra_price
                    )
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = CashierBlue,
                unfocusedIndicatorColor = CashierLightGray
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )

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
            label = {
                Text(
                    text = stringResource(
                        id = R.string.title_selling_price
                    )
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = CashierBlue,
                unfocusedIndicatorColor = CashierLightGray
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
    }
}

@Composable
private fun QuantitySegment(
    onMinusClick: () -> Unit,
    onPlusClick: () -> Unit,
    quantityAndUnitText: String
) {
    Column {
        Text(
            modifier = Modifier
                .padding(
                    top = 24.dp
                ),
            text = stringResource(id = R.string.title_quantity),
            color = Color.Black,
            fontSize = 16.sp
        )

        CounterComposable(
            modifier = Modifier.fillMaxWidth(),
            onMinusClick = onMinusClick,
            onPlusClick = onPlusClick,
            text = quantityAndUnitText
        )
    }
}

@Composable
private fun ProductUnitSegment(
    units: List<ProductUnit>,
    selectedUnit: ProductUnit,
    onUnitChanged: (ProductUnit) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                top = 20.dp
            )
    ) {
        LazyRow {
            items(units) { unit ->
                ProductUnitChip(
                    unit = unit,
                    isSelected = selectedUnit == unit,
                    onSelectionChanged = onUnitChanged
                )
            }
        }
    }
}

@Composable
private fun ProductUnitChip(
    unit: ProductUnit,
    isSelected: Boolean = false,
    onSelectionChanged: (ProductUnit) -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .padding(4.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(10.dp),
        color = if (isSelected) {
            CashierBlue
        } else {
            Color.White
        }
    ) {
        Row(
            modifier = Modifier
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        onSelectionChanged(unit)
                    }
                )
        ) {
            Text(
                text = unit.value,
                style = MaterialTheme.typography.body2,
                color = if (isSelected) {
                    Color.White
                } else {
                    CashierBlue
                },
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun CategoryDropDownSectionPreview() {
    CashierTheme {
        CategoryDropDownSegment(
            dropDownExpanded = true,
            onDismissDropDown = { },
            categoryItems = listOf(ProductCategory.empty()),
            onCategoryClick = {},
            selectedCategory = ProductCategory.empty(),
            onDropDownClick = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true,
)
private fun ProductUnitSegmentPreview() {
    CashierTheme {
        ProductUnitSegment(
            units = listOf(
                ProductUnit.KG,
                ProductUnit.PIECE
            ),
            selectedUnit = ProductUnit.KG,
            onUnitChanged = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true,
)
private fun QuantitySegmentPreview() {
    CashierTheme {
        QuantitySegment(
            onMinusClick = { },
            onPlusClick = { },
            quantityAndUnitText = "3 pc"
        )
    }
}

@Composable
@Preview(
    showBackground = true,
)
private fun ProductUnitChipPreview() {
    CashierTheme {
        ProductUnitChip(unit = ProductUnit.KG)
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun ShowPricesSegmentPreview() {
    CashierTheme {
        PricesSegment(
            purchasePrice = "100",
            setPurchasePrice = {},
            extraPrice = "200",
            setExtraPrice = {},
            sellingPrice = "34",
            setSellingPrice = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true,
)
private fun CreateEditProductScreenMainSegmentPreview() {
    CashierTheme {
        CreateEditProductScreenMainSegment(
            productName = "product",
            setProductName = {},
            units = listOf(
                ProductUnit.KG,
                ProductUnit.GRAM,
                ProductUnit.METER
            ),
            selectedUnit = ProductUnit.KG,
            onUnitChanged = {},
            onMinusClick = {},
            onPlusClick = {},
            quantityAndUnitText = "3 pc",
            purchasePrice = "",
            setPurchasePrice = {},
            extraPrice = "",
            setExtraPrice = {},
            sellingPrice = "",
            setSellingPrice = {},
            barcode = "",
            setBarcode = {},
            dropDownExpanded = true,
            onDismissDropDown = {},
            categoryItems = listOf(ProductCategory.empty()),
            onCategoryClick = {},
            selectedCategory = ProductCategory.empty(),
            onDropDownClick = {},
            onScanClick = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun CreateEditProductScreenPreview() {
    CashierTheme {
        CreateEditProductScreen(
            onBackPressed = {},
            onCreateProductClick = {},
            productName = "product",
            setProductName = {},
            units = listOf(
                ProductUnit.KG,
                ProductUnit.GRAM,
                ProductUnit.METER
            ),
            selectedUnit = ProductUnit.KG,
            onUnitChanged = {},
            onMinusClick = {},
            onPlusClick = {},
            quantityAndUnitText = "3 pc",
            purchasePrice = "",
            setPurchasePrice = {},
            extraPrice = "",
            setExtraPrice = {},
            sellingPrice = "",
            setSellingPrice = {},
            barcode = "",
            setBarcode = {},
            dropDownExpanded = true,
            onDismissDropDown = {},
            categoryItems = listOf(ProductCategory.empty()),
            onCategoryClick = {},
            createEditFlow = CreateEditFlow.EDIT,
            onDropDownClick = {},
            onScanClick = {}
        )
    }
}