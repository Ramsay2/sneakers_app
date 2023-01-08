package com.sachin.sneaker_app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "sneakers")
data class SneakerResponse(
    val id: Int?,
    val brand: String?,
    val colorway: String?,
    val name: String?,
    val retailPrice: Int?,
    val shoe: String?,
    val imageUrl: String?,
    val smallImageUrl: String?,
    var isInCart : Int? = 0
): Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sneaker_column")
    var dataId :Int? = null
}