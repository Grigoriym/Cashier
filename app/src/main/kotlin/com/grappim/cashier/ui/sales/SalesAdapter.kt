package com.grappim.cashier.ui.sales

import android.content.res.ColorStateList
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.*
import com.grappim.cashier.databinding.ItemSalesProductBinding
import com.grappim.domain.model.product.Product
import com.grappim.calculations.bigDecimalOne
import com.grappim.calculations.bigDecimalZero
import java.text.DecimalFormat

class SalesAdapter(
    private val dfSimple: DecimalFormat,
    private val clickListener: SalesItemClickListener
) : RecyclerView.Adapter<SalesAdapter.SaleProductViewHolder>() {

    private val productEntities: MutableList<Product> = mutableListOf()

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

    private fun onBasketClick(product: Product, position: Int) {
        if (product.basketCount <= bigDecimalZero()) {
            addProduct(product, position)
        } else {
            removeProduct(product, position)
        }
    }

    private fun addProduct(product: Product, position: Int) {
        product.basketCount = product.basketCount + bigDecimalOne()
        clickListener.addProduct(product)
        notifyItemChanged(position, product)
    }

    private fun minusProduct(product: Product, position: Int) {
        if (product.basketCount <= bigDecimalOne()) {
            removeProduct(product, position)
        } else {
            product.basketCount = product.basketCount - bigDecimalOne()
            clickListener.removeProduct(product)
            notifyItemChanged(position, product)
        }
    }

    private fun removeProduct(product: Product, position: Int) {
        product.basketCount = bigDecimalZero()
        clickListener.removeProduct(product)
        notifyItemChanged(position, product)
    }

    override fun getItemCount(): Int = productEntities.size

    fun updateProducts(newProductEntities: List<Product>) {
        productEntities.clear()
        productEntities.addAll(newProductEntities)
        notifyDataSetChanged()
    }

    inner class SaleProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding by viewBinding(ItemSalesProductBinding::bind)
    }
}