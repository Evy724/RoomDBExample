package com.revature.roomdbexample.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.revature.roomdbexample.datamodels.Customer

@Dao
interface CustomerData {

    @Query("SELECT * FROM Customer")
    fun fetchAllCustomers():LiveData< List<Customer> >

    //a suspend function delays the coroutine for a given time without blocking a thread
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer:Customer)

    // : separates the value from the column
    @Query("DELETE FROM Customer WHERE id = :id")
    suspend fun deleteCustomerById(id:Int)
}