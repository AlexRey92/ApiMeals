package com.e.mealapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MealAdapter: ListAdapter<MealResponse, MealAdapter.ViewHolder>(DiffCallBack) {
    inner class ViewHolder(private val view:View):RecyclerView.ViewHolder(view){
        val idmeal:TextView=view.findViewById(R.id.textViewID)
        val categoryMeal:TextView=view.findViewById(R.id.textViewCategory)
        val imageMeal:ImageView=view.findViewById(R.id.imageViewThumb)
        val descriptionMeal:TextView=view.findViewById(R.id.textViewDescription)
        val image=imageMeal.toString()
        fun onBind(meal:MealResponse){
            idmeal.text=meal.idCategory.toString()
            categoryMeal.text=meal.strCategory
            Picasso.get().load(image).into(imageMeal)
            descriptionMeal.text=meal.strCategoryDescription
        }



    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val myView:View=LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false)
        return ViewHolder(myView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val positionMeal=getItem(position)
            holder.onBind(positionMeal)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<MealResponse>() {
        override fun areItemsTheSame(oldItem: MealResponse, newItem: MealResponse): Boolean {
            return  oldItem.idCategory == newItem.idCategory
        }

        override fun areContentsTheSame(oldItem: MealResponse, newItem: MealResponse): Boolean {
            return oldItem == newItem
        }
    }


}