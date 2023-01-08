package com.sachin.sneaker_app.di

import android.content.Context
import androidx.room.Room
import com.sachin.sneaker_app.database.SneakerDao
import com.sachin.sneaker_app.database.SneakerDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): SneakerDataBase {
        val builder = Room.databaseBuilder(
            context,
            SneakerDataBase::class.java,
            "sneaker_database"
        )
        builder.fallbackToDestructiveMigration()
        return builder.build()
    }

    @Provides
    fun providerSneakerDao(sneakerDataBase: SneakerDataBase) : SneakerDao{
        return sneakerDataBase.sneakersDao()
    }


}