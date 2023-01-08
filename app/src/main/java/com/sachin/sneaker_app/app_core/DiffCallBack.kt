package com.sachin.sneaker_app.app_core

import androidx.recyclerview.widget.DiffUtil


abstract class DiffCallBack<E> : DiffUtil.Callback() {
    protected var oldList: MutableList<E> = mutableListOf()
    protected var newList: MutableList<E> = mutableListOf()

    fun setData(oldList: List<E>, newList: List<E>) {
        this.oldList.clear()
        this.oldList.addAll(oldList)
        this.newList.clear()
        this.newList.addAll(newList)
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }
}