package com.grappim.cashier.ui.selectinfo.stock

import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.vipulasri.timelineview.TimelineView
import com.grappim.cashier.R
import com.grappim.cashier.core.extensions.getBlue
import com.grappim.cashier.core.extensions.getGray
import com.grappim.cashier.core.extensions.inflate
import com.grappim.cashier.databinding.ItemSelectOutletTimelineBinding

class SelectInfoProgressAdapter(

) : RecyclerView.Adapter<SelectInfoProgressAdapter.OutletProgressViewHolder>() {

    private val items: MutableList<StockProgressItem> = mutableListOf()

    inner class OutletProgressViewHolder(
        view: View,
        viewType: Int
    ) : RecyclerView.ViewHolder(view) {
        val viewBinding: ItemSelectOutletTimelineBinding by viewBinding(
            ItemSelectOutletTimelineBinding::bind
        )

        private val timeline: TimelineView = viewBinding.timeline

        init {
            timeline.initLine(viewType)
        }
    }

    override fun getItemViewType(position: Int): Int =
        TimelineView.getTimeLineViewType(position, itemCount)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OutletProgressViewHolder =
        OutletProgressViewHolder(
            parent.inflate(R.layout.item_select_outlet_timeline),
            viewType
        )

    override fun onBindViewHolder(holder: OutletProgressViewHolder, position: Int) {
        holder.run {
            val item = items[holder.bindingAdapterPosition]
            holder.viewBinding.textProgress.setText(item.text)
            if (item.isActive) {
                viewBinding.textProgress.setTextColor(
                    itemView.context.getBlue()
                )
                viewBinding.timeline.setEndLineColor(
                    itemView.context.getBlue(),
                    holder.itemViewType
                )
                viewBinding.timeline.marker = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.ic_check_circle_blue
                )

            } else {
                viewBinding.textProgress.setTextColor(
                    itemView.context.getGray()
                )
                viewBinding.timeline.setEndLineColor(
                    itemView.context.getGray(),
                    holder.itemViewType
                )
                viewBinding.timeline.setStartLineColor(
                    itemView.context.getGray(),
                    holder.itemViewType
                )
                viewBinding.timeline.marker = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.ic_check_circle_gray
                )
            }
        }
    }

    fun setItems(newItems: List<StockProgressItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size
}

data class StockProgressItem(
    @StringRes val text: Int,
    val isActive: Boolean
)