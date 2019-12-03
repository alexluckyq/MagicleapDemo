package com.magicleap.assignment.model

import com.google.gson.annotations.SerializedName

class CoffeeList {

    @SerializedName("coffees")
    var coffeeList: List<Coffee>? = null

}