package com.example.community_basket.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val location: String,
    val price: Float,
    val unit: String
)