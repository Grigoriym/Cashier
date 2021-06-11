package com.grappim.cashier.ui.sales

import android.content.res.ColorStateList
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.bigDecimalOne
import com.grappim.cashier.core.extensions.bigDecimalZero
import com.grappim.cashier.core.extensions.getBlue
import com.grappim.cashier.core.extensions.getWhite
import com.grappim.cashier.core.extensions.inflate
import com.grappim.cashier.core.extensions.setSafeOnClickListener
import com.grappim.cashier.core.extensions.setStandardSettings
import com.grappim.cashier.data.db.entity.ProductEntity
import com.grappim.cashier.databinding.ItemSalesProductBinding
import java.text.DecimalFormat

class SalesAdapter(
    private val dfSimple: DecimalFormat,
    private val clickListener: SalesItemClickListener
) : RecyclerView.Adapter<SalesAdapter.SaleProductViewHolder>() {

    private val productEntities: MutableList<ProductEntity> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SalesAdapter.SaleProductViewHolder =
        SaleProductViewHolder(parent.inflate(R.layout.item_sales_product))

    override fun onBindViewHolder(holder: SalesAdapter.SaleProductViewHolder, position: Int) {
        with(holder) {
            val product = productEntities[holder.bindingAdapterPosition]
            binding.textName.text = product.name
            binding.textPrice.text = itemView.context.getString(
                R.string.title_price_with_currency,
                dfSimple.format(product.sellingPrice)
            )
            binding.textBasketCount.text = itemView.context.getString(
                R.string.title_basket_count,
                dfSimple.format(product.basketCount)
            )
            binding.imageProduct.load("") {
                setStandardSettings()
            }
            if (product.basketCount <= bigDecimalZero()) {
                binding.buttonBasket.backgroundTintList =
                    ColorStateList.valueOf(itemView.context.getWhite())
                binding.buttonBasket.iconTint =
                    ColorStateList.valueOf(itemView.context.getBlue())
            } else {
                binding.buttonBasket.backgroundTintList =
                    ColorStateList.valueOf(itemView.context.getBlue())
                binding.buttonBasket.iconTint =
                    ColorStateList.valueOf(itemView.context.getWhite())
            }
            binding.buttonBasket.setSafeOnClickListener {
                onBasketClick(product, holder.bindingAdapterPosition)
            }
            binding.buttonPlus.setOnClickListener {
                addProduct(product, holder.bindingAdapterPosition)
            }
            binding.buttonMinus.setOnClickListener {
                minusProduct(product, holder.bindingAdapterPosition)
            }
        }
    }

    private fun onBasketClick(productEntity: ProductEntity, position: Int) {
        if (productEntity.basketCount <= bigDecimalZero()) {
            addProduct(productEntity, position)
        } else {
            removeProduct(productEntity, position)
        }
    }

    private fun addProduct(productEntity: ProductEntity, position: Int) {
        productEntity.basketCount = productEntity.basketCount + bigDecimalOne()
        clickListener.addProduct(productEntity)
        notifyItemChanged(position, productEntity)
    }

    private fun minusProduct(productEntity: ProductEntity, position: Int) {
        if (productEntity.basketCount <= bigDecimalOne()) {
            removeProduct(productEntity, position)
        } else {
            productEntity.basketCount = productEntity.basketCount - bigDecimalOne()
            clickListener.removeProduct(productEntity)
            notifyItemChanged(position, productEntity)
        }
    }

    private fun removeProduct(productEntity: ProductEntity, position: Int) {
        productEntity.basketCount = bigDecimalZero()
        clickListener.removeProduct(productEntity)
        notifyItemChanged(position, productEntity)
    }

    override fun getItemCount(): Int = productEntities.size

    fun updateProducts(newProductEntities: List<ProductEntity>) {
        productEntities.clear()
        productEntities.addAll(newProductEntities)
        notifyDataSetChanged()
    }

    inner class SaleProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding by viewBinding(ItemSalesProductBinding::bind)
    }
}