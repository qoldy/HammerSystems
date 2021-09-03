package com.example.hammer.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hammer.R
import com.example.hammer.data.Meal
import com.squareup.picasso.Picasso

class MealsAdapter  (val meals:ArrayList<Meal>): RecyclerView.Adapter<MealsAdapter.Holder>() {
    class Holder(item: View): RecyclerView.ViewHolder(item){
        var mealName: TextView?=null
        var mealImg: ImageView?=null
        init{
            mealName=item.findViewById(R.id.mealName)
            mealImg=item.findViewById(R.id.mealImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_item, parent, false)
        return Holder(item)
    }

    override fun getItemCount(): Int { return meals.size }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.mealName?.text=meals[position].name
        Picasso.get()
            .load(meals[position].imgUrl)
            .into(holder.mealImg)
    }
}