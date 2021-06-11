package com.grappim.cashier.ui.waybill.list

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.getOffsetDateTimeFromString
import com.grappim.cashier.core.extensions.inflate
import com.grappim.cashier.core.extensions.setSafeOnClickListener
import com.grappim.cashier.core.utils.DateTimeUtils
import com.grappim.cashier.databinding.ItemAcceptanceBinding
import com.grappim.cashier.databinding.ItemAcceptanceHeaderBinding
import com.grappim.cashier.domain.waybill.Waybill
import com.grappim.cashier.ui.waybill.WaybillStatus
import java.text.DecimalFormat

class WaybillListPagingAdapter(
    private val listener: WaybillListClickListener,
    private val dfSimple: DecimalFormat
) : PagingDataAdapter<PagingDataModel<Waybill>, RecyclerView.ViewHolder>(DIFF_UTIL) {

    private val dtfDateTime = DateTimeUtils.getDateTimePatternStandard()

    companion object {
        private const val TYPE_ACCEPTANCE = 0
        private const val TYPE_HEADER = 1

        val DIFF_UTIL = object : DiffUtil.ItemCallback<PagingDataModel<Waybill>>() {
            override fun areContentsTheSame(
                oldItem: PagingDataModel<Waybill>,
                newItem: PagingDataModel<Waybill>
            ): Boolean = oldItem == newItem

            override fun areItemsTheSame(
                oldItem: PagingDataModel<Waybill>,
                newItem: PagingDataModel<Waybill>
            ): Boolean =
                (oldItem is PagingDataModel.Item && newItem is PagingDataModel.Item &&
                    oldItem.item.id == newItem.item.id)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position) ?: return) {
            is PagingDataModel.Separator -> {
                (holder as HeaderViewHolder).viewBinding.textDate.text = item.text
            }
            is PagingDataModel.Item -> {
                val waybill = item.item

                with(holder as AcceptanceViewHolder) {
                    viewBinding.textNumber.text = waybill.number
                    viewBinding.textDateTime.text = dtfDateTime.format(
                        waybill.updatedOn.getOffsetDateTimeFromString()
                    )
                    viewBinding.textTotalCost.text = itemView.context.getString(
                        R.string.title_price_with_currency,
                        dfSimple.format(waybill.totalCost)
                    )
                    when (waybill.status) {
                        WaybillStatus.ACTIVE -> {
                            viewBinding.textStatus.background = ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.shape_green_round_15
                            )
                            viewBinding.textStatus.text =
                                itemView.context.getString(R.string.title_active)
                        }
                        WaybillStatus.DRAFT -> {
                            viewBinding.textStatus.background = ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.shape_blue_round_15
                            )
                            viewBinding.textStatus.text =
                                itemView.context.getString(R.string.title_draft)
                        }
                    }

                    itemView.setSafeOnClickListener {
                        listener.onWaybillClick(waybill)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_HEADER -> HeaderViewHolder(parent.inflate(R.layout.item_acceptance_header))
            else -> AcceptanceViewHolder(parent.inflate(R.layout.item_acceptance))
        }

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is PagingDataModel.Separator -> TYPE_HEADER
            is PagingDataModel.Item -> TYPE_ACCEPTANCE
            null -> throw IllegalStateException("Unknown view")
        }

    inner class AcceptanceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val viewBinding: ItemAcceptanceBinding by viewBinding(ItemAcceptanceBinding::bind)
    }

    inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val viewBinding: ItemAcceptanceHeaderBinding by viewBinding(ItemAcceptanceHeaderBinding::bind)
    }
}