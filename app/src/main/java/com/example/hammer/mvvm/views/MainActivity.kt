package com.example.hammer.mvvm.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hammer.R
import com.example.hammer.mvvm.viewmodels.CategoriesVM
import com.example.hammer.mvvm.viewmodels.MealVM
import com.example.hammer.utils.CategoriesAdapter
import com.example.hammer.utils.MealsAdapter
import com.example.hammer.utils.VMFactory

class MainActivity:AppCompatActivity() {
    private lateinit var categoriesVM: CategoriesVM
    private lateinit var categoriesList:RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var categoriesLayoutManager: LinearLayoutManager

    private lateinit var mealsVM: MealVM
    private lateinit var mealsList:RecyclerView
    private lateinit var mealsAdapter: MealsAdapter
    private lateinit var mealsLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    //инициализация основных элементов
    private fun init(){
        //для списка категорий
        categoriesList=findViewById(R.id.cat_list)
        categoriesLayoutManager= LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        categoriesAdapter= CategoriesAdapter(ArrayList(), this)
        categoriesList.adapter=categoriesAdapter
        categoriesList.layoutManager=categoriesLayoutManager
        categoriesAdapter.setOnItemClickListener(object:CategoriesAdapter.ClickListener{
            override fun onItemClick(v: View, position: Int) {
                categoriesVM.liveData.value?.get(position)?.let { mealsVM.getMeals(it) }
            }
        })

        //для списка блюд
        mealsList=findViewById(R.id.meal_list)
        mealsLayoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mealsAdapter= MealsAdapter(ArrayList())
        mealsList.adapter=mealsAdapter
        mealsList.layoutManager=mealsLayoutManager

        //инициализация viewmodel-ей
        val factory = VMFactory()
        val provider = ViewModelProvider(this, factory)
        categoriesVM=provider.get(CategoriesVM::class.java)
        mealsVM=provider.get(MealVM::class.java)

        observeData()
        categoriesVM.getCategories()
    }

    //при изменении данных во вьюмоделях обновляются списки
    @SuppressLint("NotifyDataSetChanged")
    private fun observeData(){
        categoriesVM.liveData.observe(this,{
            categoriesVM.liveData.value?.let { it1 -> categoriesAdapter.categories.addAll(it1) }
            categoriesVM.liveData.value?.get(0)?.let { it1 -> mealsVM.getMeals(it1) }
            categoriesAdapter.notifyDataSetChanged()
        })
        mealsVM.liveData.observe(this,{
            mealsAdapter.meals.clear()
            mealsVM.liveData.value?.let { it1 -> mealsAdapter.meals.addAll(it1) }
            mealsAdapter.notifyDataSetChanged()
        })
    }
}