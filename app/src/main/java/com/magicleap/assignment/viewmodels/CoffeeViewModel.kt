package com.magicleap.assignment.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.magicleap.assignment.model.Coffee
import com.magicleap.assignment.model.CoffeeList
import com.magicleap.assignment.network.MagicLeapService
import com.magicleap.assignment.network.OnRequestCallback

class CoffeeViewModel: ViewModel() {

    private val coffeeListLiveData: MutableLiveData<List<Coffee>?> = MutableLiveData()

    fun queryCoffeeList(context: Context) {
        MagicLeapService.retrieveCoffeeList(context, object: OnRequestCallback<CoffeeList> {
            override fun onSuccess(response: CoffeeList?) {
                val coffeeList =  mutableListOf<Coffee>()
                response?.coffeeList?.let {
                    coffeeList.addAll(it)
                }
                postUpdate(coffeeList)
            }

            override fun onFailure(errorMessage: String) {
                postUpdate(null)
            }
        })
    }

    fun getCoffeeLiveData() = coffeeListLiveData

    private fun postUpdate(dataList: List<Coffee>?) {
//        updateTweetCache(query, geocode, tweetList)
        this.coffeeListLiveData.postValue(dataList)
    }

}