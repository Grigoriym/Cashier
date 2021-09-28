package com.grappim.cashier.ui.waybill.details

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.inflate
import com.grappim.cashier.core.extensions.setSafeOnClickListener
import com.grappim.cashier.core.extensions.setStandardSettings
import com.grappim.cashier.databinding.ItemProductBinding
import com.grappim.domain.model.waybill.WaybillProduct
import java.text.DecimalFormat

class WaybillProductsAdapter(
    private val dfSimple: DecimalFormat,
    private val onProductClick: (product: WaybillProduct) -> Unit,
) : PagingDataAdapter<WaybillProduct, WaybillProductsAdapter.WaybillProductViewHolder>(DIFF_UTIL) {

    companion object {
        val DIFF_UTIL = object : DiffUtil.ItemCallback<WaybillProduct>() {
            override fun areItemsTheSame(
                oldItem: WaybillProduct,
                newItem: WaybillProduct
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: WaybillProduct,
                newItem: WaybillProduct
            ): Boolean = oldItem.id == newItem.id

        }
    }

    override fun onBindViewHolder(
        holder: WaybillProductsAdapter.WaybillProductViewHolder,
        position: Int
    ) {
        val product = getItem(position) ?: return
        with(holder.viewBinding) {
            textName.text = product.name
            textPrice.text = holder.itemView.context.getString(
                R.string.title_price_with_currency,
                dfSimple.format(product.sellingPrice)
            )
            imageProduct.load("") {
                setStandardSettings()
            }
            textCountAndUnit.text = holder.itemView.context.getString(
                R.string.title_basket_count,
                dfSimple.format(product.amount)
            )

            holder.itemView.setSafeOnClickListener {
                onProductClick.invoke(product)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WaybillProductsAdapter.WaybillProductViewHolder =
        WaybillProductViewHolder(parent.inflate(R.layout.item_product))

    inner class WaybillProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val viewBinding: ItemProductBinding by viewBinding(ItemProductBinding::bind)
    }
}