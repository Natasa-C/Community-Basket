package com.example.community_basket.repository

import androidx.lifecycle.LiveData
import com.example.community_basket.data.ProductDao
import com.example.community_basket.model.Product

class ProductRepository(private val productDao: ProductDao) {
    val readAllData: LiveData<List<Product>> = productDao.readAllData()

    suspend fun addProduct(product: Product){
        productDao.addProduct(product)
    }

    suspend fun updateProduct(product: Product){
        productDao.updateProduct(product)
    }
}
