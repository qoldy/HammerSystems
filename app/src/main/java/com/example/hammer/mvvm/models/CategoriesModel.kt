package com.example.hammer.mvvm.models

import android.util.Log
import com.example.hammer.mvvm.viewmodels.CategoriesVM
import com.example.hammer.networking.RetrofitService
import com.example.hammer.networking.responses.CategoriesResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CategoriesModel(val vm:CategoriesVM) {
    //получение категорий блюд
    fun getCategories(){
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            RetrofitService.getInstance().getCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({response->onResponse(response)}, {error->onError(error)})
        )
    }

    //ошибку выводим в лог
    private fun onError(error:Throwable){
        Log.e("catsError",error.message.toString())
    }

    //при успехе запроса передаем его результат в viewmodel
    private fun onResponse(response: CategoriesResponse){
        vm.onResponse(response.categories)
    }

}