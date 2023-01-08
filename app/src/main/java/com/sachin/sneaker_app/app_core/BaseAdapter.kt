package com.sachin.sneaker_app.app_core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<ITEM> : RecyclerView.Adapter<BaseViewHolder<*, *>>() {

    private var items: MutableList<ITEM> = ArrayList()
    private var itemListSize: Int = 0
    abstract val itemLayoutId: Int
    abstract val diffCallBack: DiffCallBack<ITEM>

    fun addItems(itemList: List<ITEM>) {
        this.items.addAll(itemList)
        this.notifyDataSetChanged()
    }

    fun appendItems(itemList: List<ITEM>) {
        this.items.addAll(itemList)
        this.notifyDataSetChanged()
    }

    fun addItemWithSize(itemList: List<ITEM>, itemListSize: Int) {
        this.items.addAll(itemList)
        this.itemListSize = itemListSize
        this.notifyDataSetChanged()
    }

    fun updateData(newItems: List<ITEM>) {
        if (this.items.size > 1 && newItems.isNotEmpty()) {
            diffCallBack.let {
                it.setData(this.items, newItems)
                val diffResult = DiffUtil.calculateDiff(it)
                this.items.clear()
                this.items.addAll(newItems)
                diffResult.dispatchUpdatesTo(this)
            }
        } else {
            this.items.clear()
            this.items.addAll(newItems)
            this.notifyDataSetChanged()
        }
    }

    fun getItem(position: Int): ITEM {
        return items[position]
    }

    fun updateAndAppendData(newItems: List<ITEM>) {
        if (this.items.size > 1 && newItems.isNotEmpty()) {
            diffCallBack.let {
                val current = this.items
                it.setData(current, newItems)
                val diffResult = DiffUtil.calculateDiff(it)
                this.items.addAll(newItems)
                diffResult.dispatchUpdatesTo(this)
            }
        } else {
            this.items.addAll(newItems.toMutableList())
            this.notifyDataSetChanged()
        }
    }


    fun getSize(): Int {
        return items.size
    }
    override fun getItemCount(): Int {
           return if (itemListSize != 0 && itemListSize <= items.size)
                 itemListSize
            else items.size
    }

    private fun checkIfItemEmpty(): Boolean {
        return items.isEmpty()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*, *>, position: Int) {
        holder.onBind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*, *> {
        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            getItemLayoutId(viewType), parent, false
        )
        return getViewHolder(viewDataBinding, getItemViewType(viewType))
    }

    private fun getItemLayoutId(viewType: Int): Int {
        return itemLayoutId

    }

    override fun getItemViewType(position: Int): Int {
        return if (checkIfItemEmpty()) VIEW_TYPE_EMPTY else VIEW_TYPE_NORMAL
    }

    abstract fun getViewHolder(
        viewDataBinding: ViewDataBinding,
        viewType: Int
    ): BaseViewHolder<*, *>


    override fun onViewRecycled(holder: BaseViewHolder<*, *>) {
        holder.onRecycle(holder.absoluteAdapterPosition)
        super.onViewRecycled(holder)
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<*, *>) {
        holder.viewDetached(holder.absoluteAdapterPosition)
        super.onViewDetachedFromWindow(holder)
    }

    companion object {
        const val VIEW_TYPE_EMPTY = 0
        const val VIEW_TYPE_NORMAL = 1
    }

}