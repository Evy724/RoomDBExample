package com.revature.roomdbexample.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.revature.roomdbexample.model.repository.CustomerRepository
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class SelectViewModel(appObj: Application) : AndroidViewModel(appObj) {

    private var repository: CustomerRepository = CustomerRepository(appObj)

    suspend fun select(id: Int) {
        repository.selectCustomerById(id)
    }
}