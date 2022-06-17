package com.grappim.uikit.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.grappim.domain.model.Product
import com.grappim.uikit.R
import com.grappim.uikit.compose.button.StandardFilledButton
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierTheme

@Composable
fun ItemAddProductCompose(
    modifier: Modifier = Modifier,
    product: Product,
    onMinusClick: (Product) -> Unit,
    onPlusClick: (Product) -> Unit,
    onCartClick: ((Product) -> Unit)? = null,
    showCart: Boolean = true
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {

            }
    ) {
        ConstraintLayout {
            val (image,
                title,
                price,
                cart,
                counter,
                divider
            ) = createRefs()

            Image(
                painter = painterResource(
                    id = R.drawable.ic_image_placeholder
                ),
                contentDescription = "",
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
                    }
            )

            Text(
                modifier = Modifier
                    .constrainAs(title) {
                        top.linkTo(
                            anchor = image.top
                        )
                        start.linkTo(
                            anchor = image.end,
                            margin = 10.dp
                        )
                        end.linkTo(
                            anchor = cart.start,
                            margin = 10.dp
                        )
                        width = Dimension.fillToConstraints
                    },
                text = product.name,
                color = Color.Black,
                fontSize = 14.sp
            )

            Text(
                text = stringResource(
                    id = R.string.title_price_with_currency,
                    product.sellingPrice.toString()
                ),
                modifier = Modifier
                    .constrainAs(price) {
                        end.linkTo(
                            anchor = parent.end,
                            margin = 16.dp
                        )
                        top.linkTo(
                            anchor = title.top
                        )
                    },
                color = CashierBlue,
                fontSize = 16.sp
            )

            if (showCart) {
                StandardFilledButton(
                    onButtonClick = {
                        onCartClick?.invoke(product)
                    },
                    iconDrawable = R.drawable.ic_shopping_cart,
                    modifier = Modifier
                        .constrainAs(cart) {
                            end.linkTo(
                                anchor = parent.end,
                                margin = 16.dp
                            )
                            bottom.linkTo(
                                anchor = counter.bottom
                            )
                            top.linkTo(
                                anchor = counter.top,
                            )
                            height = Dimension.fillToConstraints
                        },
                    backgroundColor = Color.White,
                    iconTint = CashierBlue
                )
            }

            CounterComposable(
                modifier = Modifier
                    .constrainAs(counter) {
                        start.linkTo(
                            anchor = title.start
                        )
                        top.linkTo(
                            anchor = title.bottom,
                            margin = 8.dp
                        )
                    },
                onMinusClick = {
                    onMinusClick(product)
                },
                onPlusClick = {
                    onPlusClick(product)
                },
                text = "${product.amount} ${product.unit.value}"
            )

            Divider(
                modifier = Modifier
                    .constrainAs(divider) {
                        start.linkTo(
                            anchor = title.start
                        )
                        top.linkTo(
                            anchor = counter.bottom,
                            margin = 16.dp
                        )
                    }
            )
        }
    }
}

@Composable
@Preview
private fun CounterPreview() {
    CashierTheme {
        CounterComposable(
            onMinusClick = {},
            onPlusClick = {},
            text = "1 pc"
        )
    }
}

@Composable
@Preview
private fun ItemAddProductPreview() {
    CashierTheme {
        ItemAddProductCompose(
            product = Product.empty(),
            onPlusClick = {},
            onMinusClick = {},
            onCartClick = {},
        )
    }
}

@Composable
@Preview
private fun ItemAddProductPreview2() {
    CashierTheme {
        ItemAddProductCompose(
            product = Product.empty(),
            onPlusClick = {},
            onMinusClick = {},
            onCartClick = {},
            showCart = false
        )
    }
}