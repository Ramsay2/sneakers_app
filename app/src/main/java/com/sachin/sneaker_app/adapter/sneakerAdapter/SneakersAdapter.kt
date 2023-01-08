package com.sachin.sneaker_app.adapter.sneakerAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.sachin.sneaker_app.R
import com.sachin.sneaker_app.databinding.SneakerItemViewBinding
import com.sachin.sneaker_app.model.SneakerResponse


class SneakersAdapter(val sneakerList: MutableList<SneakerResponse>) :
    RecyclerView.Adapter<SneakersAdapter.SneakerViewHolder>() , SneakerItemViewModel.Callback {


    interface Callback : SneakerItemViewModel.Callback

    var callBack : Callback? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SneakerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutBinding: SneakerItemViewBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.sneaker_item_view, parent, false)
        return SneakerViewHolder(layoutBinding)
    }

    override fun onBindViewHolder(holder: SneakerViewHolder, position: Int) {
        holder.onBind(sneakerList[position])
    }

    override fun getItemCount(): Int {
        return sneakerList.size
    }


    inner class SneakerViewHolder(val viewBinding: SneakerItemViewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {


        fun onBind(sneakerResponse: SneakerResponse) {
          viewBinding.viewModel =  SneakerItemViewModel(sneakerResponse , this@SneakersAdapter)

        }
    }

    override fun addToCart(sneakerResponse: SneakerResponse) {
        callBack?.addToCart(sneakerResponse)
    }

    override fun onItemClicked(sneakerResponse: SneakerResponse) {
        callBack?.onItemClicked(sneakerResponse)
    }
}

class SneakerItemViewModel(val sneakerResponse: SneakerResponse ,val callback: Callback)  {
    val sneakerName = ObservableField("${sneakerResponse.name}")
    val imageLink = ObservableField("${sneakerResponse.imageUrl}")
    val sneakerPrice = ObservableField("$${sneakerResponse.retailPrice}")
    val isAddedToCart = ObservableField(sneakerResponse.isInCart == 1)

    fun onItemClick(){
        callback.onItemClicked(sneakerResponse)
    }

    fun addToCart(){
        val sneaker = sneakerResponse.copy()
        sneaker.isInCart = 1
        isAddedToCart.set(false)
        callback.addToCart(sneaker)
    }

    interface Callback{
        fun addToCart(sneakerResponse: SneakerResponse)
        fun onItemClicked(sneakerResponse: SneakerResponse)
    }
}