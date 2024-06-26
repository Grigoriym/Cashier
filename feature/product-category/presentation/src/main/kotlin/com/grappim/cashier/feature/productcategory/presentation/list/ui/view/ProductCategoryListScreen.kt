package com.grappim.cashier.feature.productcategory.presentation.list.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.grappim.cashier.feature.productcategory.domain.model.ProductCategory
import com.grappim.uikit.R
import com.grappim.uikit.compose.BaseTopAppBar
import com.grappim.uikit.compose.CashierImage
import com.grappim.uikit.compose.CashierText
import com.grappim.uikit.compose.button.BigActionButtonCompose
import com.grappim.uikit.theme.CashierTheme

@Composable
internal fun ProductCategoryListScreen(
    onBackPressed: () -> Unit,
    onCreateCategoryClick: () -> Unit,
    categories: List<ProductCategory>,
    onCategoryClick: (ProductCategory) -> Unit
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            BaseTopAppBar(
                toolbarTitle = stringResource(id = R.string.title_product_categories)
            ) {
                onBackPressed()
            }
        },
        bottomBar = {
            BigActionButtonCompose(
                buttonText = stringResource(id = R.string.action_create),
                modifier = Modifier,
                onButtonClick = onCreateCategoryClick
            )
        }
    ) {
        ProductCategoryListMainSegment(
            modifier = Modifier.padding(it),
            categories = categories,
            onCategoryClick = onCategoryClick
        )
    }
}

@Composable
private fun ProductCategoryListMainSegment(
    modifier: Modifier = Modifier,
    categories: List<ProductCategory>,
    onCategoryClick: (ProductCategory) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        ProductCategoryListSegment(
            categories = categories,
            onCategoryClick = onCategoryClick
        )
    }
}

@Composable
private fun ProductCategoryListSegment(
    categories: List<ProductCategory>,
    onCategoryClick: (ProductCategory) -> Unit
) {
    LazyColumn {
        items(categories) { item ->
            ProductCategoryItem(
                productCategory = item,
                onCategoryClick = onCategoryClick
            )
        }
    }
}

@Composable
private fun ProductCategoryItem(
    productCategory: ProductCategory,
    onCategoryClick: (ProductCategory) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCategoryClick(productCategory)
            }
    ) {
        ConstraintLayout {
            val (
                image,
                title
            ) = createRefs()

            CashierImage(
                painter = painterResource(
                    id = R.drawable.ic_image_placeholder
                ),
                modifier = Modifier
                    .size(50.dp)
                    .constrainAs(image) {
                        top.linkTo(
                            anchor = parent.top,
                            margin = 16.dp
                        )
                        start.linkTo(
                            anchor = parent.start,
                            margin = 16.dp
                        )
                        bottom.linkTo(
                            anchor = parent.bottom,
                            margin = 16.dp
                        )
                    }
            )

            CashierText(
                text = productCategory.name,
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(
                            anchor = image.top
                        )
                        bottom.linkTo(
                            anchor = image.bottom
                        )
                        start.linkTo(
                            anchor = image.end,
                            margin = 10.dp
                        )
                    }
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun ProductCategoryListSegmentPreview() {
    CashierTheme {
        ProductCategoryListSegment(
            categories = listOf(
                ProductCategory.empty(),
                ProductCategory.empty(),
                ProductCategory.empty()
            ),
            onCategoryClick = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun ProductCategoryItemPreview() {
    CashierTheme {
        ProductCategoryItem(
            productCategory = ProductCategory.empty(),
            onCategoryClick = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun ProductCategoryListScreenPreview() {
    CashierTheme {
        ProductCategoryListScreen(
            onBackPressed = {},
            onCreateCategoryClick = {},
            categories = listOf(
                ProductCategory.empty(),
                ProductCategory.empty(),
                ProductCategory.empty()
            ),
            onCategoryClick = {}
        )
    }
}
