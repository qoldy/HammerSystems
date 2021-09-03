package com.example.hammer.networking.responses

import com.example.hammer.data.Category
import com.google.gson.annotations.SerializedName

data class CategoriesResponse (
    @SerializedName("categories")
    val categories:ArrayList<Category>
    )