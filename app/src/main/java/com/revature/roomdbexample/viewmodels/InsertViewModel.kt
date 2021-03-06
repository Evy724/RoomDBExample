package com.revature.roomdbexample.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.revature.roomdbexample.model.datamodels.Customer
import com.revature.roomdbexample.model.repository.CustomerRepository
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class InsertViewModel(appObj: Application) : AndroidViewModel(appObj) {

    private var repository: CustomerRepository = CustomerRepository(appObj)

    suspend fun insert(customer: Customer) {
        repository.insertCustomer(customer)
    }
}