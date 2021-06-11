package com.grappim.cashier.ui.selectinfo.stock

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.inflate
import com.grappim.cashier.core.extensions.showOrGone
import com.grappim.cashier.databinding.ItemSelectInfoBinding
import com.grappim.cashier.domain.outlet.Stock

class SelectStockAdapter(
    private val listener: StockListClickListener
) : RecyclerView.Adapter<SelectStockAdapter.SelectStockViewHolder>() {

    private val items = mutableListOf<Stock>()
    private var selectedItem: Int = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectStockAdapter.SelectStockViewHolder =
        SelectStockViewHolder(parent.inflate(R.layout.item_select_info))

    override fun onBindViewHolder(holder: SelectStockAdapter.SelectStockViewHolder, position: Int) {
        with(holder) {
            val item = items[holder.bindingAdapterPosition]
            viewBinding.textName.text = item.name
            viewBinding.textName.isEnabled = selectedItem == holder.bindingAdapterPosition
            viewBinding.imageChecked.showOrGone(selectedItem == holder.bindingAdapterPosition)

            itemView.setOnClickListener {
                val oldPosition = selectedItem
                selectedItem = holder.bindingAdapterPosition
                if (oldPosition != -1) {
                    notifyItemChanged(oldPosition)
                }
                if (oldPosition == holder.bindingAdapterPosition) {
                    selectedItem = -1
                }
                notifyItemChanged(holder.bindingAdapterPosition)
                listener.onOutletClick()
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun addItems(newList: List<Stock>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    fun getSelectedItem(): Stock? = try {
        items[selectedItem]
    } catch (e: Exception) {
        null
    }

    inner class SelectStockViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val viewBinding: ItemSelectInfoBinding by viewBinding(ItemSelectInfoBinding::bind)
    }
}