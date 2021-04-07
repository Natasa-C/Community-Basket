package com.example.community_basket.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.community_basket.model.Product

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)

    //
//    @Update
//    fun updateUsers(vararg users: User?)
//    //room uses the primary key to match passed entity instances to rows in the database
//
//    //room uses the primary key to match passed entity instances to rows in the database
//    @Delete
//    fun delete(user: User?)
//
    @Query("SELECT * FROM products ORDER BY id ASC")
    fun readAllData(): LiveData<List<Product>>
//
//    @Query("SELECT firstName FROM user")
//    fun loadFirstName(): List<String?>?
//
//    @Query("SELECT * FROM user WHERE age > :minAge")
//    fun loadAllUsersOlderThan(minAge: Int): Array<User?>?
//
//    @Query("SELECT * FROM user WHERE id IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray?): List<User?>?
//
//    @Query("SELECT * FROM user WHERE firstName LIKE :search OR lastName LIKE :search LIMIT 1")
//    fun findUserWithName(search: String?): User?
}