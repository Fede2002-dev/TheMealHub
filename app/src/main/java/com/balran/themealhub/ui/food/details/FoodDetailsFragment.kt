package com.balran.themealhub.ui.food.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.balran.data.FoodsRepository
import com.balran.data.LocalFoodsRepository
import com.balran.domain.Food
import com.balran.domain.Resource
import com.balran.themealhub.R
import com.balran.themealhub.databinding.FragmentFoodDetailsBinding
import com.balran.themealhub.framework.retrofit.RemoteFoodDataSource
import com.balran.themealhub.framework.room.AppDatabase
import com.balran.themealhub.framework.room.LocalFoodDataSource
import com.balran.themealhub.model.FoodModel
import com.balran.themealhub.model.toFoodDomain
import com.balran.themealhub.utils.Constants
import com.balran.themealhub.utils.hide
import com.balran.themealhub.utils.show
import com.balran.usecases.local.CheckMealFavouriteUseCase
import com.balran.usecases.local.DeleteFavouriteUseCase
import com.balran.usecases.local.InsertFavoriteUseCase
import com.balran.usecases.remote.GetFoodByIdUseCase
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip


class FoodDetailsFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailsBinding

    private val detailsViewModel: FoodDetailsFragmentViewModel by viewModels {
        val localFoodsRepository =
            LocalFoodsRepository(LocalFoodDataSource(AppDatabase.getDatabase(requireContext())))
        FoodDetailsViewModelFactory(
            GetFoodByIdUseCase(FoodsRepository(RemoteFoodDataSource())),
            InsertFavoriteUseCase(localFoodsRepository),
            DeleteFavouriteUseCase(localFoodsRepository),
            CheckMealFavouriteUseCase(localFoodsRepository)
        )
    }
    private var idFood: String?=null
    private var food: Food?=null
    private var isFav=false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Inflate view
        binding = FragmentFoodDetailsBinding.inflate(layoutInflater,container,false)
        setupObserver()
        binding.emptyErrorContainer.button.setOnClickListener { detailsViewModel.getFoodData(idFood!!) }

        requireArguments().let {
            idFood = it.getString(Constants.ARG_IDFOOD)
            if(idFood!=null) detailsViewModel.getFoodData(idFood!!)

            food=it.getParcelable<FoodModel>(Constants.ARG_FOOD)?.toFoodDomain()
            if(food!=null) {idFood=food!!.idMeal; setupData(food!! )}
        }

        return binding.root
    }

    private fun setupObserver() {
        detailsViewModel.foodData.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> binding.progressCircular.show()
                is Resource.Success -> {
                    setupData(it.data);binding.progressCircular.hide()
                    binding.emptyErrorContainer.root.hide()}
                is Resource.Failure -> {
                    binding.progressCircular.hide()
                    binding.emptyErrorContainer.tvErrorMessage.text=it.exception.message
                    binding.emptyErrorContainer.root.show()
                }
            }
        })
    }

    private fun setupData(food: Food) {
        binding.tvMealTitle.text = food.strMeal
        binding.tvStrInstructions.text = food.strInstructions
        Glide.with(requireContext()).load(food.strMealThumb).fitCenter().into(binding.ivMeal)
        isFav = detailsViewModel.checkFavouriteMeal(idFood!!)
        updateButtonIcon()

        if (food.ingredients.isNotEmpty()) {
            addChips(food)
        }

        binding.fabFavourite.setOnClickListener {
            detailsViewModel.saveOrDeleteMeal(food)
            isFav = !isFav
            updateButtonIcon()
        }
    }

    private fun addChips(food: Food) {
        for (ingredient in food.ingredients) {
            if (!ingredient.name.equals("") && ingredient.name != null) {
                val chip = Chip(requireContext())
                chip.text = ingredient.name
                binding.chipGroup.addView(chip)
            }
        }
    }

    private fun updateButtonIcon() {
        binding.fabFavourite.setImageResource(
            when (isFav) {
                true -> R.drawable.ic_baseline_favorite_24
                else -> R.drawable.ic_baseline_favorite_border_24
            }
        )
    }
}