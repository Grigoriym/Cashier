package com.grappim.payment_method

import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.domain.model.payment.PaymentMethodType
import com.grappim.extensions.inflate
import com.grappim.extensions.setSafeOnClickListener
import com.grappim.payment_method.databinding.ItemPaymentMethodBinding

class PaymentMethodAdapter(
    private val listener: PaymentMethodClickListener
) : RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodViewHolder>() {

    private val items = mutableListOf<PaymentMethod>()

    inner class PaymentMethodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val viewBinding: ItemPaymentMethodBinding by viewBinding(ItemPaymentMethodBinding::bind)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder =
        PaymentMethodViewHolder(parent.inflate(R.layout.item_payment_method))

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {
        val item = items[holder.bindingAdapterPosition]
        holder.viewBinding.textTitle.setText(item.text)
        holder.itemView.setSafeOnClickListener {
            listener.onClick(item)
        }
    }

    fun setItems(list: List<PaymentMethod>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size
}

data class PaymentMethod(
    @StringRes val text: Int,
    val type: PaymentMethodType
)

interface PaymentMethodClickListener {
    fun onClick(paymentMethod: PaymentMethod)
}