package com.arun.androidThings.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    val thingsGetter = MutableLiveData<Things>()

    fun getServicesApiCall(): MutableLiveData<Things> {

        val call = RetrofitClient.serviceInterface.getThings()

        call.enqueue(object: Callback<Things> {
            override fun onFailure(call: Call<Things>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<Things>,
                response: Response<Things>
            ) {
                val data = response.body()
                Log.v("DEBUG : ", data.toString())
                thingsGetter.postValue(data)
            }
        })
        return thingsGetter
    }
}