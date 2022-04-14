package com.grappim.product_category.presentation.create_edit.ui.view

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grappim.product_category.presentation.R
import com.grappim.product_category.presentation.create_edit.model.CreateEditFlow
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.button.BigActionButtonCompose
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierLightGray
import com.grappim.uikit.theme.CashierTheme

@Composable
internal fun CreateEditProductCategoryScreen(
    categoryName: String,
    setCategoryName: (String) -> Unit,
    createEditFlow: CreateEditFlow,
    onBackPressed: () -> Unit,
    onCreateCategoryClick: () -> Unit
) {
    val title = when (createEditFlow) {
        CreateEditFlow.EDIT -> {
            stringResource(id = R.string.title_edit_category)
        }
        CreateEditFlow.CREATE -> {
            stringResource(id = R.string.title_create_category)
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
                onButtonClick = onCreateCategoryClick
            )
        }
    ) {
        CreateEditCategoryMainSegment(
            modifier = Modifier,
            categoryName = categoryName,
            setCategoryName = setCategoryName
        )
    }
}

@Composable
private fun CreateEditCategoryMainSegment(
    modifier: Modifier = Modifier,
    categoryName: String,
    setCategoryName: (String) -> Unit
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
            CategoryName(
                categoryName = categoryName,
                setCategoryName = setCategoryName
            )
        }
    }
}

@Composable
private fun CategoryName(
    categoryName: String,
    setCategoryName: (String) -> Unit,
) {
    TextField(
        value = categoryName,
        onValueChange = setCategoryName,
        modifier = Modifier
            .fillMaxWidth(),
        placeholder = {
            Text(text = stringResource(id = R.string.title_category_name))
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = CashierBlue,
            unfocusedIndicatorColor = CashierLightGray
        )
    )
}

@Composable
@Preview(
    showBackground = true
)
private fun CreateEditCategoryMainSegmentPreview() {
    CashierTheme {
        CreateEditCategoryMainSegment(
            categoryName = "category1",
            setCategoryName = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun CategoryNamePreview() {
    CashierTheme {
        CategoryName(categoryName = "Category 1", setCategoryName = {})
    }
}