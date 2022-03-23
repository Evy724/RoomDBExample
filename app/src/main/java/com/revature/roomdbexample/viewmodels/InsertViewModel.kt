package com.revature.roomdbexample.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.revature.roomdbexample.datamodels.Customer
import com.revature.roomdbexample.repository.CustomerRepository
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class InsertViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: CustomerRepository = CustomerRepository(application)

    suspend fun insert(customer: Customer) {
        repository.insertCustomer(customer)
    }
}