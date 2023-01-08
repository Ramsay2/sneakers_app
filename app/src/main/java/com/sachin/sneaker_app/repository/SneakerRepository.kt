package com.sachin.sneaker_app.repository

import androidx.lifecycle.LiveData
import com.sachin.sneaker_app.database.SneakerDao
import com.sachin.sneaker_app.model.SneakerResponse
import javax.inject.Inject

class SneakerRepository @Inject constructor(private val sneakerDao: SneakerDao) {

    fun getAllSneakers(): LiveData<List<SneakerResponse>> {

        return sneakerDao.getAllSneakersFromDB()
    }

    suspend fun addSneakersToDB(sneakerList : List<SneakerResponse>) {
        sneakerDao.addAllSneakerToDB(sneakerList)
    }

    suspend fun addToCart(sneakerResponse: SneakerResponse) {
        sneakerDao.addSneakerToCartDB(sneakerResponse)
    }

    fun getAllCartProducts() : LiveData<List<SneakerResponse>> {
       return sneakerDao.getAllCartProducts()
    }

  suspend  fun removeSneakerFromCart() {
        sneakerDao.removeSneakerFromCartDB()
    }

}