package com.example.hammer.mvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hammer.data.Category
import com.example.hammer.data.Meal
import com.example.hammer.mvvm.models.MealModel

//Аналогично для категорий
class MealVM : ViewModel() {
    var liveData = MutableLiveData<ArrayList<Meal>>()
    private var model = MealModel(this)

    fun getMeals(category:Category){ model.getMeals(category.name) }

    fun onResponse(response:ArrayList<Meal>){liveData.value=response}
}