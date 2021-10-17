package com.grappim.bag

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.grappim.calculations.bigDecimalOne
import com.grappim.calculations.bigDecimalZero
import com.grappim.domain.model.product.Product
import com.grappim.extensions.hide
import com.grappim.extensions.inflate
import com.grappim.extensions.setStandardSettings
import com.grappim.uikit.databinding.ItemSalesProductBinding
import java.text.DecimalFormat

class BagAdapter(
    private val dfSimple: DecimalFormat,
    private val clickListener: BagItemClickListener
) : RecyclerView.Adapter<BagAdapter.BagViewHolder>() {

    private val productEntities: MutableList<Product> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BagViewHolder =
        BagViewHolder(parent.inflate(R.layout.item_sales_product))

    override fun onBindViewHolder(holder: BagViewHolder, position: Int) {
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
            binding.buttonBasket.hide()
            binding.buttonPlus.setOnClickListener {
                addProduct(product, holder.bindingAdapterPosition)
            }
            binding.buttonMinus.setOnClickListener {
                minusProduct(product, holder.bindingAdapterPosition)
            }
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

    private fun removeProduct(productEntity: Product, position: Int) {
        productEntity.basketCount = bigDecimalZero()
        clickListener.removeProduct(productEntity)
        notifyItemChanged(position, productEntity)
    }

    override fun getItemCount(): Int = productEntities.size

    fun updateProducts(newProductEntities: List<Product>) {
        productEntities.clear()
        productEntities.addAll(newProductEntities)
        notifyDataSetChanged()
    }

    inner class BagViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding by viewBinding(ItemSalesProductBinding::bind)
    }
}