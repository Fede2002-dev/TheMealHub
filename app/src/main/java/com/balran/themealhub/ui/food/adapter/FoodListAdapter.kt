package com.balran.themealhub.ui.food.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.balran.domain.Food
import com.balran.themealhub.R
import com.balran.themealhub.databinding.FoodListItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class FoodListAdapter(private val context: Context,
                      val itemClickListener: OnCocktailClickListener)
    : RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {

    private var foodList= listOf<Food>()

    interface OnCocktailClickListener{
        fun onCocktailClick(food:Food)
    }

    fun setDrinkList(drinkList:List<Food>){
        this.foodList=drinkList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding:FoodListItemBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Food, position: Int){

            Glide.with(context).load(item.strMealThumb)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.loading)
                .placeholder(R.mipmap.loading)
                .into(binding.ivFoodThumb)
            binding.tvFoodName.text=item.strMeal

            itemView.setOnClickListener { itemClickListener.onCocktailClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodListAdapter.ViewHolder {
        return ViewHolder(FoodListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FoodListAdapter.ViewHolder, position: Int) {
        holder.bind(foodList[position], position)
    }

    override fun getItemCount(): Int = foodList.size
}