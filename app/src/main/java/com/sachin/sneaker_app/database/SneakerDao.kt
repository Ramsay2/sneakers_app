package com.sachin.sneaker_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sachin.sneaker_app.model.SneakerResponse


@Dao
interface SneakerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllSneakerToDB(sneakerItem: List<SneakerResponse>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSneakerToCartDB(sneakerItem: SneakerResponse)

    @Query("SELECT * FROM sneakers ")
    fun getAllSneakersFromDB(): LiveData<List<SneakerResponse>>

    @Query("SELECT * FROM sneakers where isInCart= 1")
    fun getAllCartProducts(): LiveData<List<SneakerResponse>>

    @Query("DELETE FROM sneakers where isInCart= 1")
    suspend fun removeSneakerFromCartDB()
}