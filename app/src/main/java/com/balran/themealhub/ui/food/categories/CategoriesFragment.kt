package com.balran.themealhub.ui.food.categories

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.balran.data.CategoriesRepository
import com.balran.domain.Category
import com.balran.domain.Resource
import com.balran.themealhub.R
import com.balran.themealhub.databinding.FragmentCategoriesBinding
import com.balran.themealhub.framework.RemoteCategoryDataSource
import com.balran.themealhub.ui.food.adapter.CategoriesListAdapter
import com.balran.themealhub.utils.Constants
import com.balran.themealhub.utils.hide
import com.balran.themealhub.utils.show
import com.balran.themealhub.utils.showToast
import com.balran.usecases.GetAllCategoriesUseCase


class CategoriesFragment : Fragment(), CategoriesListAdapter.OnCategoryClick {
    private val binding by lazy{FragmentCategoriesBinding.inflate(layoutInflater)}
    private val adapter by lazy{CategoriesListAdapter(requireContext(),this)}
    private val viewModel by viewModels<CategoriesFragmentViewModel> { CategoriesFragmentViewModelFactory(
        GetAllCategoriesUseCase(CategoriesRepository(RemoteCategoryDataSource()))
    ) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
                is Resource.Loading -> binding.progressCircular.show()
                is Resource.Success -> {adapter.setCategoriesList(it.data);binding.progressCircular.hide()}
                is Resource.Failure -> {
                    showToast("Failure: ${it.exception.message}")
                    binding.progressCircular.hide()
                    binding.emptyErrorContainer.root.show()
                }
            }
        })
    }

    override fun onCategoryClick(category: Category) {
        val bundle = Bundle()
        bundle.putString(Constants.ARG_CATEGORY, category.strCategory)
        findNavController().navigate(R.id.action_categoriesFragment_to_foodListFragment, bundle)
    }
}