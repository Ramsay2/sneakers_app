package com.sachin.sneaker_app.view.sneakers_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachin.sneaker_app.adapter.sneakerAdapter.SneakersAdapter
import com.sachin.sneaker_app.model.SneakerResponse
import com.sachin.sneaker_app.repository.SneakerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SneakerViewModel @Inject constructor(val repository: SneakerRepository) : ViewModel(),
     SneakersAdapter.Callback {

    var navigator : Navigator? = null
    var _sneakerData = mutableListOf<SneakerResponse>()
    var imageLink = MutableLiveData("")
    var sneakerName = MutableLiveData("")
    var sneakerPrice = MutableLiveData("")
    var sneakerDetails = MutableLiveData("")



    fun addProductToDB() {
        for (i in 0..50) {
            val imageUrl =
                if(i % 2 == 0){
                    "https://images.unsplash.com/photo-1600185365483-26d7a4cc7519?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1625&q=80"
                }else{
                    "https://images.unsplash.com/photo-1549298916-b41d501d3772?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1712&q=80"
                }

            val sneaker = SneakerResponse(
                id = i,
                brand = "Nike",
                colorway = "Green",
                name = "Nike Air $i",
                retailPrice = i * 14 + 10,
                shoe = "Classic and sleek, the Nike Air Max 97 is a stylish hole in one. This special edition channels a vintage look while keeping the futuristic design of the original with its iconic rippled lines and hidden lacing system. Crisp leather pairs with airy knits and mesh to create a layered upper steeped in texture. Plus, full-length Max Air cushioning helps keep your every step comfortable.",
                imageUrl = imageUrl,
                smallImageUrl = ""
            )
            _sneakerData.add(sneaker)
        }
        viewModelScope.launch {
            repository.addSneakersToDB(_sneakerData)
        }

    }


    fun getAllSneakers(): LiveData<List<SneakerResponse>> {
        return repository.getAllSneakers()
    }

    fun openCartPage() {
        navigator?.openCartPage()
    }

    override fun addToCart(sneakerResponse: SneakerResponse) {
        viewModelScope.launch {
            repository.addToCart(sneakerResponse)
        }
    }

    override fun onItemClicked(sneakerResponse: SneakerResponse) {
        imageLink.value = (sneakerResponse.imageUrl)
        sneakerName.value = (sneakerResponse.name)
        sneakerPrice.value = (sneakerResponse.retailPrice.toString())
        sneakerDetails.postValue(sneakerResponse.shoe)
        navigator?.showDetailsDialog()
    }


}

interface Navigator {
    fun openCartPage()
    fun showDetailsDialog()
}