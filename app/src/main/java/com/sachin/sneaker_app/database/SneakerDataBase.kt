package com.sachin.sneaker_app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sachin.sneaker_app.model.SneakerResponse


@Database(entities = [SneakerResponse::class] , version = 2)
abstract class SneakerDataBase :RoomDatabase() {
    abstract fun sneakersDao() : SneakerDao
}