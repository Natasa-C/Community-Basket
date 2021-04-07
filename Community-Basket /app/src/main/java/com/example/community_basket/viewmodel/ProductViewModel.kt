package com.example.community_basket.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.community_basket.data.AppDatabase
import com.example.community_basket.repository.ProductRepository
import com.example.community_basket.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Product>>
    private val repository: ProductRepository

    init {
        val productDao = AppDatabase.getDatabase(
            application
        ).productDao()
        repository =
            ProductRepository(productDao)
        readAllData = repository.readAllData
    }

    fun addProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(product)
        }
    }

    fun updateProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateProduct(product)
        }
    }
}