package com.grappim.products.create

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.grappim.domain.model.base.ProductUnit
import com.grappim.domain.model.product.Category
import com.grappim.products.R
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.BigActionButtonCompose
import com.grappim.uikit.compose.CounterComposable
import com.grappim.uikit.compose.StandardFilledButton
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
    categoryItems: List<Category>,
    onCategoryClick: (Category) -> Unit,
    createEditFlow: CreateEditFlow,
    selectedCategory: Category? = null,
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
                toolbarTitle = title,
                backButtonTitle = stringResource(id = R.string.title_products)
            ) {
                onBackPressed()
            }
        }
    ) {
        CreateEditProductScreenMainSegment(
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
            onCreateProductClick = onCreateProductClick,
            buttonTitle = title,
            selectedCategory = selectedCategory,
            onScanClick = onScanClick
        )
    }
}

@Composable
private fun CreateEditProductScreenMainSegment(
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
    categoryItems: List<Category>,
    onCategoryClick: (Category) -> Unit,
    onCreateProductClick: () -> Unit,
    buttonTitle: String,
    selectedCategory: Category?,
    onScanClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp
            )
    ) {
        item {
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
                    backgroundColor = Color.White,
                    focusedIndicatorColor = CashierBlue,
                    unfocusedIndicatorColor = CashierLightGray
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
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = CashierBlue,
                    unfocusedIndicatorColor = CashierLightGray
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

        item {
            BigActionButtonCompose(
                buttonText = buttonTitle,
                modifier = Modifier,
                onButtonClick = onCreateProductClick
            )
        }
    }
}

@Composable
private fun CategoryDropDownSegment(
    dropDownExpanded: Boolean,
    onDismissDropDown: () -> Unit,
    categoryItems: List<Category>,
    onCategoryClick: (Category) -> Unit,
    selectedCategory: Category?,
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

@Composable
private fun PricesSegment(
    purchasePrice: String,
    setPurchasePrice: (String) -> Unit,
    extraPrice: String,
    setExtraPrice: (String) -> Unit,
    sellingPrice: String,
    setSellingPrice: (String) -> Unit
) {
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
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = CashierBlue,
                unfocusedIndicatorColor = CashierLightGray
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
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = CashierBlue,
                unfocusedIndicatorColor = CashierLightGray
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
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = CashierBlue,
                unfocusedIndicatorColor = CashierLightGray
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
            categoryItems = listOf(Category.empty()),
            onCategoryClick = {},
            selectedCategory = Category.empty(),
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
            categoryItems = listOf(Category.empty()),
            onCategoryClick = {},
            onCreateProductClick = {},
            buttonTitle = "Edit",
            selectedCategory = Category.empty(),
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
            categoryItems = listOf(Category.empty()),
            onCategoryClick = {},
            createEditFlow = CreateEditFlow.EDIT,
            onDropDownClick = {},
            onScanClick = {}
        )
    }
}