package com.example.hammer.mvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hammer.data.Category
import com.example.hammer.mvvm.models.CategoriesModel

class CategoriesVM:ViewModel() {
    //наблюдаемые данные
    var liveData = MutableLiveData<ArrayList<Category>>()
    //модель
    private var model = CategoriesModel(this)

    //получение категорий, вызывается из view
    fun getCategories(){ model.getCategories() }

    //обработка ответа
    fun onResponse(response:ArrayList<Category>){liveData.value=response}
}