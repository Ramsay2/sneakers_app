package com.sachin.sneaker_app.view.cart_fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachin.sneaker_app.model.SneakerResponse
import com.sachin.sneaker_app.repository.SneakerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(val repository: SneakerRepository) : ViewModel() {

    val _sneakerData = mutableListOf<SneakerResponse>()

    fun getCartProducts(): LiveData<List<SneakerResponse>> {
        return repository.getAllCartProducts()
    }

    fun clearCart() {
        Log.e("frag", "clearCart")
        viewModelScope.launch {
            repository.removeSneakerFromCart()
        }
    }
}
