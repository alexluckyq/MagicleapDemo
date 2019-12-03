package com.magicleap.assignment.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.magicleap.assignment.model.Coffee
import com.magicleap.assignment.network.MagicLeapService
import com.magicleap.assignment.network.OnRequestCallback

class CoffeeDetailViewModel: ViewModel() {

    private val coffeeDetailLiveData: MutableLiveData<Coffee> = MutableLiveData()

    fun postData(coffee: Coffee?) {
        this.coffeeDetailLiveData.value = coffee
    }

    fun queryCoffeeDetailById(context: Context, id: String) {
        MagicLeapService.retrieveCoffeeDetail(context, id, object: OnRequestCallback<Coffee> {
            override fun onSuccess(response: Coffee?) {
                postData(response)
            }

            override fun onFailure(errorMessage: String) {
                postData(null)
            }
        })
    }

    fun getCoffeeDetailLiveData() = coffeeDetailLiveData
}