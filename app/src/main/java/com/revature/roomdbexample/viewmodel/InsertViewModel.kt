package com.revature.roomdbexample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.revature.roomdbexample.datamodels.Customer
import com.revature.roomdbexample.repository.CustomerRepository

class InsertViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var repository: CustomerRepository

    init {

        repository = CustomerRepository(application)
    }

    suspend fun insert(customer: Customer) {
        repository.insertCustomer(customer)
    }
}