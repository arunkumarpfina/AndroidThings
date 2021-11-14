package com.arun.androidThings.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arun.androidThings.data.MainActivityRepository
import com.arun.androidThings.data.Things

class MainActivityViewModel : ViewModel() {

    var thingsLiveData: MutableLiveData<Things>? = null

    fun getUser() : LiveData<Things>? {
        thingsLiveData = MainActivityRepository.getServicesApiCall()
        return thingsLiveData
    }

}