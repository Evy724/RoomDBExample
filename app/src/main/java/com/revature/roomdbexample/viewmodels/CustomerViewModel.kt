package com.revature.roomdbexample.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.revature.roomdbexample.model.datamodels.Customer
import com.revature.roomdbexample.model.repository.CustomerRepository
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class CustomerViewModel(appObj: Application) : AndroidViewModel(appObj) {

    private val customerRepository: CustomerRepository = CustomerRepository(appObj)

    fun fetchAllCustomers(): LiveData<List<Customer>> {
        return customerRepository.readAllCustomers
    }

    fun insertCustomer(customer: Customer) {
        viewModelScope.launch {
            customerRepository.insertCustomer(customer)
        }
    }
    fun deleteCustomerById(id: Int) {
        viewModelScope.launch {
            customerRepository.deleteCustomerById(id)
        }
    }
    fun selectCustomerById(id: Int) {
        viewModelScope.launch {
            customerRepository.selectCustomerById(id)
        }
    }
}