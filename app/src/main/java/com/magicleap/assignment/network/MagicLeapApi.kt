package com.magicleap.assignment.network

import retrofit2.Call
import com.magicleap.assignment.model.Coffee
import com.magicleap.assignment.model.CoffeeList
import retrofit2.http.GET
import retrofit2.http.Path

public interface MagicLeapApi {

    @GET("coffees")
    fun getCoffeeList(): Call<CoffeeList>

    @GET("coffees/{id}")
    fun getCoffeeDetail(@Path("id") id: String): Call<Coffee>

}