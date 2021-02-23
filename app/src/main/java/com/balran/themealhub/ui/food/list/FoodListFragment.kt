package com.balran.themealhub.ui.food.list

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.balran.data.FoodsRepository
import com.balran.domain.Food
import com.balran.domain.Resource
import com.balran.themealhub.R
import com.balran.themealhub.databinding.FragmentFoodListBinding
import com.balran.themealhub.framework.retrofit.RemoteFoodDataSource
import com.balran.themealhub.ui.food.adapter.FoodListAdapter
import com.balran.themealhub.utils.Constants
import com.balran.themealhub.utils.hide
import com.balran.themealhub.utils.show
import com.balran.themealhub.utils.showToast
import com.balran.usecases.remote.GetFoodsByCategory

class FoodListFragment : Fragment(), FoodListAdapter.OnCocktailClickListener {

    private lateinit var binding:FragmentFoodListBinding
    private val viewModel by viewModels<FoodListFragmentViewModel>{FoodListFragmentViewModelFactory(
        GetFoodsByCategory(FoodsRepository(RemoteFoodDataSource()))
    )}
    private val adapter by lazy { FoodListAdapter(requireContext(), this) }
    private lateinit var category:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            category=it.getString(Constants.ARG_CATEGORY)!!
            viewModel.getMealsByCategory(category)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Inflate view
        binding = FragmentFoodListBinding.inflate(layoutInflater,container,false)
        //Change span dynamically
        if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setupRecyclerView(2)
        } else {
            setupRecyclerView(3)
        }
        setupObservers()
        binding.emptyErrorContainer.button.setOnClickListener { viewModel.getMealsByCategory(category) }
        binding.emptyErrorContainer.button.setOnClickListener { viewModel.getMealsByCategory(category) }
        return binding.root
    }

    private fun setupObservers() {
        viewModel.foodData.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Loading -> {binding.progressCircular.show();binding.emptyErrorContainer.root.hide()}
                is Resource.Success -> {
                    adapter.setDrinkList(it.data);binding.progressCircular.hide()
                    binding.rvFoods.show()
                    binding.emptyErrorContainer.root.hide()}
                is Resource.Failure -> {
                    binding.progressCircular.hide()
                    binding.progressCircular.hide()
                    binding.emptyErrorContainer.root.show()
                    binding.rvFoods.hide()
                    binding.emptyErrorContainer.tvErrorMessage.text=it.exception.message }
            }
        })
    }

    private fun setupRecyclerView(columns: Int){
        binding.rvFoods.layoutManager=GridLayoutManager(requireContext(), columns)
        binding.rvFoods.adapter=adapter
        binding.rvFoods.setHasFixedSize(true)
    }

    override fun onCocktailClick(food: Food) {
        val bundle = Bundle()
        bundle.putString(Constants.ARG_IDFOOD, food.idMeal)
        findNavController().navigate(R.id.action_foodListFragment_to_foodDetailsFragment, bundle)
    }
}