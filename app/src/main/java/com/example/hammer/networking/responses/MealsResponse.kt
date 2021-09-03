package com.example.hammer.networking.responses

import com.example.hammer.data.Meal
import com.google.gson.annotations.SerializedName

data class MealsResponse (
    @SerializedName("meals")
    val meals:ArrayList<Meal>
)