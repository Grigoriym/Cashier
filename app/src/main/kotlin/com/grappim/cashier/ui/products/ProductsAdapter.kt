package com.grappim.cashier.ui.products

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.grappim.cashier.R
import com.grappim.extensions.inflate
import com.grappim.extensions.setSafeOnClickListener
import com.grappim.extensions.setStandardSettings
import com.grappim.cashier.databinding.ItemProductBinding
import com.grappim.domain.model.product.Product
import java.text.DecimalFormat

class ProductsAdapter(
    private val dfSimple: DecimalFormat,
    private val clickListener: ProductsClickListener
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private val productEntities: MutableList<Product> = mutableListOf()

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val viewBinding by viewBinding(ItemProductBinding::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(parent.inflate(R.layout.item_product))

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        with(holder) {
            val product = productEntities[holder.bindingAdapterPosition]
            viewBinding.textName.text = product.name
            viewBinding.textPrice.text = itemView.context.getString(
                R.string.title_price_with_currency,
                dfSimple.format(product.sellingPrice)
            )
            viewBinding.imageProduct.load("") {
                setStandardSettings()
            }
            viewBinding.textCountAndUnit.text = itemView.context.getString(
                R.string.title_basket_count,
                dfSimple.format(product.amount)
            )

            itemView.setSafeOnClickListener {
                clickListener.onProductClick(product)
            }
        }
    }

    fun updateProducts(newProductEntities: List<Product>) {
        productEntities.clear()
        productEntities.addAll(newProductEntities)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = productEntities.size
}