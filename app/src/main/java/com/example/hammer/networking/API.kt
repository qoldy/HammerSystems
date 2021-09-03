package com.example.hammer.networking

import com.example.hammer.networking.responses.CategoriesResponse
import com.example.hammer.networking.responses.MealsResponse
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.http.Query

interface API {
    @GET("categories.php")
    fun getCategories():Observable<CategoriesResponse>

    @GET("filter.php")
    fun getMeals(@Query("c")category:String):Observable<MealsResponse>
}