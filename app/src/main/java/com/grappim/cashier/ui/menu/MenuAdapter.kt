package com.grappim.cashier.ui.menu

import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.inflate
import com.grappim.cashier.core.extensions.setSafeOnClickListener
import com.grappim.cashier.databinding.ItemMenuBinding

class MenuAdapter(
    private val listener: MenuItemClickListener
) : RecyclerView.Adapter<MenuAdapter.MenuItemViewHolder>() {

    private val items = mutableListOf<MenuItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder =
        MenuItemViewHolder(parent.inflate(R.layout.item_menu))

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        with(holder) {
            val menuItem = items[bindingAdapterPosition]
            binding.textMenu.setText(menuItem.text)
            binding.imageMenu.setImageResource(menuItem.image)
            itemView.setSafeOnClickListener {
                listener.onItemClick(menuItem)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun setItems(newItems: List<MenuItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class MenuItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemMenuBinding by viewBinding(ItemMenuBinding::bind)
    }
}

data class MenuItem(
    val type: MenuItemType,
    @StringRes val text: Int,
    @DrawableRes val image: Int
)

enum class MenuItemType {
    SALES,
    PRODUCTS,
    ACCEPTANCE
}