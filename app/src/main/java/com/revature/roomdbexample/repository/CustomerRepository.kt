package com.revature.roomdbexample.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.revature.roomdbexample.dao.CustomerData
import com.revature.roomdbexample.datamodels.AppDatabase
import com.revature.roomdbexample.datamodels.Customer
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class CustomerRepository(application: Application) {

    private var customerDao: CustomerData

    init {

        val database = AppDatabase.getDatabase(application)

        customerDao = database.customerDao()
    }
    val readAllCustomers: LiveData<List<Customer>> = customerDao.fetchAllCustomers()

    suspend fun deleteCustomerById(id: Int) {

        customerDao.deleteCustomerById(id)
    }
    suspend fun insertCustomer(customer: Customer) {

        customerDao.insertCustomer(customer)
    }

}