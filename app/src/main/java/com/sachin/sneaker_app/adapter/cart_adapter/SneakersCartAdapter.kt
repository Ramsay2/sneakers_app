package com.sachin.sneaker_app.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.RecyclerView
import com.sachin.sneaker_app.R
import com.sachin.sneaker_app.databinding.CartItemViewBinding
import com.sachin.sneaker_app.databinding.SneakerItemViewBinding
import com.sachin.sneaker_app.model.SneakerResponse


class SneakersCartAdapter(val sneakerList: MutableList<SneakerResponse>) :
    RecyclerView.Adapter<SneakersCartAdapter.SneakerCartViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SneakerCartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutBinding: CartItemViewBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.cart_item_view, parent, false)
        return SneakerCartViewHolder(layoutBinding)
    }

    override fun onBindViewHolder(holder: SneakerCartViewHolder, position: Int) {
        holder.onBind(sneakerList[position])
    }

    override fun getItemCount(): Int {
        return sneakerList.size
    }


    inner class SneakerCartViewHolder(val viewBinding: CartItemViewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {


        fun onBind(sneakerResponse: SneakerResponse) {
            viewBinding.viewModel = SneakerItemCartViewModel(sneakerResponse)

        }
    }

}

class SneakerItemCartViewModel(val sneakerResponse: SneakerResponse )  {
    val sneakerName = ObservableField("${sneakerResponse.name}")
    val imageLink = ObservableField("${sneakerResponse.imageUrl}")
    val sneakerPrice = ObservableField("$${sneakerResponse.retailPrice}")


}
