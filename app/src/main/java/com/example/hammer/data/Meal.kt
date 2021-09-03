package com.example.hammer.data

import com.google.gson.annotations.SerializedName

data class Meal (
    @SerializedName("idMeal")
    val id:Long,
    @SerializedName("strMeal")
    val name:String,
    @SerializedName("strMealThumb")
    val imgUrl:String
        )