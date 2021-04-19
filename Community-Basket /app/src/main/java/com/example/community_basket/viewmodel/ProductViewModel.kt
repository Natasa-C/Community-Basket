package com.example.community_basket.viewmodel

import android.app.Application
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.community_basket.data.AppDatabase
import com.example.community_basket.repository.ProductRepository
import com.example.community_basket.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Product>>
    private val repository: ProductRepository
    var responseInsert = MutableLiveData<Boolean>()

    init {
        val productDao = AppDatabase.getDatabase(
            application
        ).productDao()
        repository =
            ProductRepository(productDao)
        readAllData = repository.readAllData
    }

    fun addProduct(product: Product, imageId: String) {
        viewModelScope.launch {
            val roomId = repository.addProduct(product)

            // add in Firestore
            val db = FirebaseFirestore.getInstance()
            val fireProduct: MutableMap<String, Any?> = HashMap()

            fireProduct["name"] = product.name
            fireProduct["location"] = product.location
            fireProduct["price"] = product.price
            fireProduct["unit"] = product.unit
            fireProduct["imageId"] = imageId

            db.collection("products").document(roomId.toString())
                .set(product)
                .addOnSuccessListener {
                    Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                    responseInsert.value = true
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error writing document", e)
                    responseInsert.value = false
                }
        }
    }

    fun updateProduct(product: Product, imageId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateProduct(product)

            val db = FirebaseFirestore.getInstance()
            val fireProduct: MutableMap<String, Any?> = HashMap()

            fireProduct["name"] = product.name
            fireProduct["location"] = product.location
            fireProduct["price"] = product.price
            fireProduct["unit"] = product.unit
            fireProduct["imageId"] = imageId

            db.collection("products").document(product.id.toString())
                .set(product)
                .addOnSuccessListener {
                    Log.d(ContentValues.TAG, "DocumentSnapshot successfully written!")
                    responseInsert.value = true
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error writing document", e)
                    responseInsert.value = false
                }
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteProduct(product)

            val db = FirebaseFirestore.getInstance()
            db.collection("products").document(product.id.toString())
                .delete()
                .addOnSuccessListener {
                    Log.d(
                        ContentValues.TAG,
                        "DocumentSnapshot successfully deleted!"
                    )
                }
                .addOnFailureListener { e ->
                    Log.w(
                        ContentValues.TAG,
                        "Error deleting document",
                        e
                    )
                }
        }
    }

    fun deleteAllProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllProducts()
        }
    }
}