package com.example.hammer.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hammer.mvvm.viewmodels.CategoriesVM
import com.example.hammer.mvvm.viewmodels.MealVM

class VMFactory: ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CategoriesVM::class.java))
            return CategoriesVM() as T
        if(modelClass.isAssignableFrom(MealVM::class.java))
            return MealVM() as T
        throw IllegalArgumentException ("UnknownViewModel")
    }
}