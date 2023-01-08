package com.sachin.sneaker_app.app_core

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


abstract class BaseViewHolder<VIEW_BINDING : ViewDataBinding, ITEM_VIEW_MODEL : BaseItemViewModel>
(private val viewDataBinding: VIEW_BINDING) : RecyclerView.ViewHolder(viewDataBinding.root) {

    private var viewModel: ITEM_VIEW_MODEL? = null

    abstract val itemBindingVariable: Int

    abstract fun onBind(position: Int)

    fun bindViewModel(viewModel: ITEM_VIEW_MODEL) {
        this.viewModel = viewModel
        this.viewDataBinding.setVariable(itemBindingVariable, this.viewModel)
        this.viewDataBinding.executePendingBindings()
    }

    open fun onRecycle(position: Int) {

    }

    open fun viewDetached(position: Int) {

    }
}