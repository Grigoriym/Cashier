package com.grappim.cashier.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.asBigDecimal
import com.grappim.cashier.domain.waybill.WaybillProduct
import com.grappim.cashier.ui.theme.CashierBlue
import com.grappim.cashier.ui.theme.CashierGray
import com.grappim.cashier.ui.theme.CashierLightGray
import com.grappim.cashier.ui.theme.CashierTheme

@Composable
fun ItemProductCompose(
    modifier: Modifier = Modifier,
    waybillProduct: WaybillProduct,
    onProductClick: (WaybillProduct) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onProductClick(waybillProduct)
            }
    ) {
        ConstraintLayout {
            val (productImage,
                productName,
                productPrice,
                productCountUnit,
                divider,
                arrow
            ) = createRefs()

            Image(
                painter = painterResource(
                    id = R.drawable.ic_image_placeholder
                ),
                contentDescription = "",
                modifier = Modifier
                    .size(50.dp)
                    .constrainAs(productImage) {
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
                    .constrainAs(productName) {
                        top.linkTo(
                            anchor = productImage.top
                        )
                        start.linkTo(
                            anchor = productImage.end,
                            margin = 10.dp
                        )
                        end.linkTo(
                            anchor = arrow.start,
                            margin = 10.dp
                        )
                        width = Dimension.fillToConstraints
                    },
                text = waybillProduct.name,
                color = Color.Black,
                fontSize = 14.sp
            )

            Text(
                modifier = Modifier
                    .padding(
                        end = 16.dp
                    )
                    .constrainAs(productPrice) {
                        start.linkTo(
                            anchor = productName.start
                        )
                        top.linkTo(
                            anchor = productName.bottom,
                            margin = 4.dp
                        )
                    },
                text = "${waybillProduct.sellingPrice}",
                color = CashierBlue,
                fontSize = 16.sp
            )

            Text(
                modifier = Modifier
                    .constrainAs(productCountUnit) {
                        centerVerticallyTo(productPrice)
                        start.linkTo(
                            anchor = productPrice.end,
                            margin = 8.dp
                        )
                    },
                text = waybillProduct.amount.toString(),
                fontSize = 14.sp,
                color = CashierGray
            )

            Divider(
                modifier = Modifier
                    .constrainAs(divider) {
                        start.linkTo(
                            anchor = productName.start
                        )
                        end.linkTo(
                            anchor = arrow.end
                        )
                        top.linkTo(
                            anchor = productImage.bottom,
                            margin = 4.dp
                        )
                        width = Dimension.fillToConstraints
                    },
                color = CashierLightGray,
                thickness = 1.dp
            )

            Icon(
                modifier = Modifier
                    .constrainAs(arrow) {
                        bottom.linkTo(
                            anchor = productImage.bottom
                        )
                        top.linkTo(
                            anchor = productImage.top
                        )
                        end.linkTo(
                            anchor = parent.end,
                            margin = 34.dp
                        )
                    },
                painter = painterResource(
                    id = R.drawable.ic_keyboard_arrow_right
                ),
                contentDescription = ""
            )
        }
    }
}

@Composable
@Preview
private fun ItemProductComposePreview() {
    CashierTheme {
        ItemProductCompose(
            waybillProduct = WaybillProduct(
                amount = "100".asBigDecimal(),
                barcode = "12345",
                createdOn = "",
                id = 1L,
                name = "Product name",
                productId = 2L,
                purchasePrice = "234".asBigDecimal(),
                sellingPrice = "4532".asBigDecimal(),
                updatedOn = "",
                waybillId = 45
            ),
            onProductClick = {}
        )
    }
}