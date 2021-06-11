package com.grappim.cashier.ui.selectinfo.cashbox

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.inflate
import com.grappim.cashier.core.extensions.showOrGone
import com.grappim.cashier.databinding.ItemSelectInfoBinding
import com.grappim.cashier.domain.cashbox.CashBox
import com.grappim.cashier.domain.cashier.Cashier

class SelectCashierAdapter(
    private val listener: CashierListClickListener
) : RecyclerView.Adapter<SelectCashierAdapter.SelectInfoViewHolder>() {

    private val items = mutableListOf<CashBox>()
    private var selectedItem: Int = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectCashierAdapter.SelectInfoViewHolder =
        SelectInfoViewHolder(parent.inflate(R.layout.item_select_info))

    override fun onBindViewHolder(
        holder: SelectCashierAdapter.SelectInfoViewHolder,
        position: Int
    ) {
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
                listener.onCashierClick()
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun addItems(newList: List<CashBox>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    fun getSelectedItem(): CashBox? = try {
        items[selectedItem]
    } catch (e: Exception) {
        null
    }

    inner class SelectInfoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val viewBinding: ItemSelectInfoBinding by viewBinding(ItemSelectInfoBinding::bind)
    }
}