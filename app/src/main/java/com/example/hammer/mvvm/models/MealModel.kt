package com.example.hammer.mvvm.models

import android.util.Log
import com.example.hammer.mvvm.viewmodels.MealVM
import com.example.hammer.networking.RetrofitService
import com.example.hammer.networking.responses.CategoriesResponse
import com.example.hammer.networking.responses.MealsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
//аналогично CategoriesModel, но для блюд
class MealModel(val vm: MealVM) {
    fun getMeals(category:String="Beef"){
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            RetrofitService.getInstance().getMeals(category)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response->onResponse(response)}, {error->onError(error)})
        )
    }

    private fun onError(error:Throwable){
        Log.e("mealsError",error.message.toString())
    }

    private fun onResponse(response: MealsResponse){
        vm.onResponse(response.meals)
    }
}