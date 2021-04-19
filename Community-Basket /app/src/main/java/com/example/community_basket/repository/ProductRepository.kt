package com.example.community_basket.repository

import androidx.lifecycle.LiveData
import com.example.community_basket.data.ProductDao
import com.example.community_basket.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository(private val productDao: ProductDao) {
    val readAllData: LiveData<List<Product>> = productDao.readAllData()

    suspend fun addProduct(product: Product): Long {
        var id: Long = 0
        withContext(Dispatchers.IO){
            id = productDao.addProduct(product)
        }
        return id
    }

    suspend fun updateProduct(product: Product) {
        productDao.updateProduct(product)
    }

    suspend fun deleteProduct(product: Product) {
        productDao.deleteProduct(product)
    }

    suspend fun deleteAllProducts() {
        productDao.deleteAll()
    }

}
