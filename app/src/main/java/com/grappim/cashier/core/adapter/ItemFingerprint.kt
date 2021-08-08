package com.grappim.cashier.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

interface ItemFingerprint<V : ViewBinding, I : Item> {

    fun isRelativeItem(item: Item): Boolean

    @LayoutRes
    fun getLayoutId(): Int

    fun getViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup
    ): BaseViewHolder<V, I>
}

interface Item

abstract class BaseViewHolder<out V : ViewBinding, in I : Item>(
    val binding: V
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(item: I)
}