package com.balran.themealhub.ui.food.favourites

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.balran.data.LocalFoodsRepository
import com.balran.domain.Food
import com.balran.domain.Resource
import com.balran.themealhub.R
import com.balran.themealhub.databinding.FragmentFoodListBinding
import com.balran.themealhub.framework.room.AppDatabase
import com.balran.themealhub.framework.room.LocalFoodDataSource
import com.balran.themealhub.model.toPresentationModel
import com.balran.themealhub.ui.food.adapter.FoodListAdapter
import com.balran.themealhub.utils.Constants
import com.balran.themealhub.utils.hide
import com.balran.themealhub.utils.show
import com.balran.themealhub.utils.showToast
import com.balran.usecases.local.GetAllFavFoodsUseCase

class MealFavouriteListDetailsFragment : Fragment(), FoodListAdapter.OnCocktailClickListener {

    private lateinit var binding:FragmentFoodListBinding

    private val viewModel by viewModels<MealFavouritesListViewModel>{
        MealFavouritesListViewModelFactory(
        GetAllFavFoodsUseCase(LocalFoodsRepository(LocalFoodDataSource(AppDatabase.getDatabase(requireContext()))))
    )
    }

    private val adapter by lazy { FoodListAdapter(requireContext(),this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Inflate view
        binding = FragmentFoodListBinding.inflate(layoutInflater,container,false)
        //Change span dynamically
        if(requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setupRecyclerView(2)
        }else setupRecyclerView(3)
        setupObservers()
        viewModel.getFavouriteMeals()
        return binding.root
    }

    private fun setupObservers() {
        viewModel.mealData.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {binding.progressCircular.show()}
                is Resource.Success -> {
                    adapter.setDrinkList(it.data)
                    binding.progressCircular.hide()
                    if(it.data.isEmpty()) binding.noMealsContainer.root.show()
                    else binding.noMealsContainer.root.hide()
                }
                is Resource.Failure -> {showToast("Failure: ${it.exception.message}");binding.progressCircular.hide(); binding.noMealsContainer.root.hide()}
            }
        })
    }

    private fun setupRecyclerView(columns: Int){
        binding.rvFoods.layoutManager= GridLayoutManager(requireContext(), columns)
        binding.rvFoods.adapter=adapter
        binding.rvFoods.setHasFixedSize(true)
    }

    override fun onCocktailClick(food: Food) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.ARG_FOOD, food.toPresentationModel())
        findNavController().navigate(R.id.action_mealFavouriteDetailsFragment_to_foodDetailsFragment, bundle)
    }

}