package com.example.hammer.utils

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.hammer.R
import com.example.hammer.data.Category

class CategoriesAdapter (val categories:ArrayList<Category>, val context: Context): RecyclerView.Adapter<CategoriesAdapter.Holder>() {

        private var clickListener:ClickListener?=null
        var selected=0
        inner class Holder(item:View):RecyclerView.ViewHolder(item), View.OnClickListener{
            var categoryName:TextView?=null
            init{
                categoryName=item.findViewById(R.id.name)
                if(clickListener!=null){
                    itemView.setOnClickListener(this)
                }
            }

            override fun onClick(p0: View?) {
                if(p0!=null){
                    clickListener?.onItemClick(p0,adapterPosition)
                    selected=adapterPosition
                    notifyDataSetChanged()
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val item = LayoutInflater.from(parent.context)
                .inflate(R.layout.category_item, parent, false)
            return Holder(item)
        }

        override fun getItemCount(): Int { return categories.size }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.categoryName?.text=categories[position].name
            if(position==selected){
                holder.categoryName?.setBackgroundResource(R.drawable.category_bg_selected)
                holder.categoryName?.setTextColor(ContextCompat.getColor(context, R.color.red))
            }
            else{
                holder.categoryName?.setBackgroundResource(R.drawable.category_bg)
                holder.categoryName?.setTextColor(ContextCompat.getColor(context, R.color.grey_light))
            }
        }

        fun setOnItemClickListener(clickListener: ClickListener){
            this.clickListener=clickListener
        }

        interface ClickListener{
            fun onItemClick(v:View, position: Int)
        }
}