package com.magicleap.assignment.model

import com.google.gson.annotations.SerializedName
import com.magicleap.assignment.utils.formatTime

public class Coffee {

    @SerializedName("id")
    lateinit var id: String

    @SerializedName("name")
    var name: String? = ""

    @SerializedName("description")
    var description: String? = ""

    @SerializedName("image_url")
    var imageUrl: String? = ""

    @SerializedName("long_description")
    var longDescription: String? = ""

    @SerializedName("last_updated_at")
    var lastUpdateTime: String? = ""

    val formattedTimeString: String
        get() = lastUpdateTime?.formatTime().toString()
}