package com.balran.themealhub.ui.food.categories

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.balran.data.CategoriesRepository
import com.balran.domain.Category
import com.balran.domain.Resource
import com.balran.themealhub.R
import com.balran.themealhub.databinding.FragmentCategoriesBinding
import com.balran.themealhub.databinding.FragmentFoodListBinding
import com.balran.themealhub.framework.retrofit.RemoteCategoryDataSource
import com.balran.themealhub.ui.food.adapter.CategoriesListAdapter
import com.balran.themealhub.utils.Constants
import com.balran.themealhub.utils.hide
import com.balran.themealhub.utils.show
import com.balran.usecases.remote.GetAllCategoriesUseCase


class CategoriesFragment : Fragment(), CategoriesListAdapter.OnCategoryClick {
    private lateinit var binding:FragmentCategoriesBinding
    private val adapter by lazy{CategoriesListAdapter(requireContext(),this)}
    private val viewModel by viewModels<CategoriesFragmentViewModel> { CategoriesFragmentViewModelFactory(
        GetAllCategoriesUseCase(CategoriesRepository(RemoteCategoryDataSource()))
    ) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Inflate view
        binding = FragmentCategoriesBinding.inflate(layoutInflater,container,false)
        if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            setupRecyclerView(2)
        } else {
            setupRecyclerView(3)
        }
        setupObserver()
        binding.emptyErrorContainer.button.setOnClickListener { viewModel.getCategories() }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setupRecyclerView(columns:Int){
        binding.rvCategories.layoutManager=GridLayoutManager(requireContext(),columns)
        binding.rvCategories.adapter = adapter
        binding.rvCategories.setHasFixedSize(true)
    }

    private fun setupObserver(){
        viewModel.categoriesData.observe(viewLifecycleOwner, {
            when(it){
                is Resource.Loading -> {binding.progressCircular.show(); binding.emptyErrorContainer.root.hide()}
                is Resource.Success -> {
                    adapter.setCategoriesList(it.data);binding.progressCircular.hide()
                    binding.rvCategories.show()
                    binding.emptyErrorContainer.root.hide()}
                is Resource.Failure -> {
                    binding.progressCircular.hide()
                    binding.emptyErrorContainer.root.show()
                    binding.rvCategories.hide()
                    binding.emptyErrorContainer.tvErrorMessage.text=it.exception.message}
            }
        })
    }

    override fun onCategoryClick(category: Category) {
        val bundle = Bundle()
        bundle.putString(Constants.ARG_CATEGORY, category.strCategory)
        findNavController().navigate(R.id.action_categoriesFragment_to_foodListFragment, bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favourites -> {
                findNavController().navigate(R.id.action_categoriesFragment_to_mealFavouriteDetailsFragment)
                false
            }
            else -> false
        }
    }
}