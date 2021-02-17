package com.balran.themealhub.ui.food.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.balran.domain.Category
import com.balran.domain.Food
import com.balran.themealhub.R
import com.balran.themealhub.databinding.FoodListItemBinding
import com.balran.themealhub.databinding.ListCategoriesItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class CategoriesListAdapter(private val context: Context,
                            val itemClickListener: OnCategoryClick)
    : RecyclerView.Adapter<CategoriesListAdapter.ViewHolder>() {

    private var categoriesList= listOf<Category>()

    interface OnCategoryClick{
        fun onCategoryClick(category: Category)
    }

    fun setCategoriesList(categoriesList:List<Category>){
        this.categoriesList=categoriesList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding:ListCategoriesItemBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Category, position: Int){
            Glide.with(context).load(item.strCategoryThumb)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.loading)
                .placeholder(R.mipmap.loading)
                .into(binding.ivCategory)
            binding.tvCategoryName.text = item.strCategory
            itemView.setOnClickListener { itemClickListener.onCategoryClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesListAdapter.ViewHolder {
        return ViewHolder(ListCategoriesItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CategoriesListAdapter.ViewHolder, position: Int) {
        holder.bind(categoriesList[position], position)
    }

    override fun getItemCount(): Int = categoriesList.size
}