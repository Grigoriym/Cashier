package com.grappim.uikit.compose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.grappim.domain.model.product.Product
import com.grappim.uikit.R
import com.grappim.uikit.theme.CashierBlue
import com.grappim.uikit.theme.CashierGray
import com.grappim.uikit.theme.CashierTheme

@Composable
fun ItemAddProductCompose(
    modifier: Modifier = Modifier,
    product: Product,
    onMinusClick: (Product) -> Unit,
    onPlusClick: (Product) -> Unit,
    onCartClick: (Product) -> Unit
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
                            anchor = cart.end
                        )
                        top.linkTo(
                            anchor = title.top
                        )
                    },
                color = CashierBlue,
                fontSize = 16.sp
            )

            StandardFilledButton(
                onButtonClick = {
                    onCartClick(product)
                },
                iconDrawable = R.drawable.ic_shopping_cart,
                modifier = Modifier
                    .constrainAs(cart) {
                        end.linkTo(
                            anchor = parent.end,
                            margin = 16.dp
                        )
                        bottom.linkTo(
                            anchor = parent.bottom
                        )
                        top.linkTo(
                            anchor = price.bottom,
                        )
                    },
                backgroundColor = Color.White,
                iconTint = CashierBlue
            )

            Counter(
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
                text = "1 pc"
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
private fun Counter(
    modifier: Modifier = Modifier,
    onMinusClick: () -> Unit,
    onPlusClick: () -> Unit,
    text: String
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onMinusClick,
            shape = RoundedCornerShape(
                topStart = 16.dp,
                bottomStart = 16.dp,
                topEnd = 0.dp,
                bottomEnd = 0.dp
            ),
            modifier = Modifier
                .width(60.dp)
                .height(30.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp
            ),
            border = BorderStroke(
                width = 1.dp,
                color = CashierGray
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Remove,
                contentDescription = ""
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(
                    color = Color.White
                )
                .border(
                    width = 1.dp,
                    color = Color.Gray
                )
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(90.dp),
                color = CashierGray,
                textAlign = TextAlign.Center
            )
        }

        Button(
            onClick = onPlusClick,
            shape = RoundedCornerShape(
                topStart = 0.dp,
                bottomStart = 0.dp,
                topEnd = 16.dp,
                bottomEnd = 16.dp
            ),
            modifier = Modifier
                .width(60.dp)
                .height(30.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp
            ),
            border = BorderStroke(
                width = 1.dp,
                color = CashierGray
            )
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = ""
            )
        }
    }
}

@Composable
@Preview
private fun CounterPreview() {
    CashierTheme {
        Counter(
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
            onCartClick = {}
        )
    }
}