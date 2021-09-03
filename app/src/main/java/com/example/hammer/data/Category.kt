package com.example.hammer.data

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("idCategory")
    val id:Long,
    @SerializedName("strCategory")
    val name:String
)
