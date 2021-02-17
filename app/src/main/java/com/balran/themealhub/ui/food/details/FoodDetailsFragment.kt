package com.balran.themealhub.ui.food.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.balran.data.FoodsRepository
import com.balran.domain.Food
import com.balran.domain.Resource
import com.balran.themealhub.databinding.FragmentFoodDetailsBinding
import com.balran.themealhub.framework.RemoteFoodDataSource
import com.balran.themealhub.utils.Constants
import com.balran.themealhub.utils.hide
import com.balran.themealhub.utils.show
import com.balran.themealhub.utils.showToast
import com.balran.usecases.GetFoodByIdUseCase
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip

class FoodDetailsFragment : Fragment() {
    private val binding by lazy{FragmentFoodDetailsBinding.inflate(layoutInflater)}
    private val detailsViewModel:FoodDetailsFragmentViewModel by viewModels { FoodDetailsViewModelFactory(
        GetFoodByIdUseCase(FoodsRepository(RemoteFoodDataSource()))
    ) }
    private lateinit var idFood:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            detailsViewModel.getFoodData(it.getString(Constants.ARG_IDFOOD)!!)
            idFood=it.getString(Constants.ARG_IDFOOD)!!
            detailsViewModel.getFoodData(idFood)        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupObserver()

        binding.emptyErrorContainer.button.setOnClickListener { detailsViewModel.getFoodData(idFood) }

        return binding.root
    }

    private fun setupObserver() {
        detailsViewModel.foodData.observe(viewLifecycleOwner,{
            when(it){
                is Resource.Loading -> binding.progressCircular.show()
                is Resource.Success -> {setupData(it.data);binding.progressCircular.hide()}
                is Resource.Failure -> {showToast("Failure: ${it.exception.message}"); binding.progressCircular.hide()}
            }
        })
    }

    private fun setupData(food: Food) {
        binding.tvMealTitle.text = food.strMeal
        binding.tvStrInstructions.text = food.strInstructions
        Glide.with(requireContext()).load(food.strMealThumb).into(binding.ivMeal)

        if(food.ingredients.isNotEmpty()){
            addChips(food)
        }
    }

    private fun addChips(food: Food) {
        for(ingredient in food.ingredients){
            if(!ingredient.name.equals("") && ingredient.name!=null){
                val chip =Chip(requireContext())
                chip.text = ingredient.name
                binding.chipGroup.addView(chip)
            }
        }
    }

}