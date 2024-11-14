package com.example.effectivemobiletest.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.FragmentFavoriteBinding
import com.example.effectivemobiletest.databinding.FragmentSearchBinding
import com.example.effectivemobiletest.presentation.adapter.common.SearchItem
import com.example.effectivemobiletest.presentation.adapter.favorite.FavoriteVacancyAdapter
import com.example.effectivemobiletest.presentation.state.FavoriteState
import com.example.effectivemobiletest.presentation.utils.DeclensionUtils
import com.example.effectivemobiletest.presentation.viewmodel.FavoriteViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {

    private val viewModel by viewModel<FavoriteViewModel>()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val adapter = FavoriteVacancyAdapter{ viewModel.deleteFavoriteVacancy(it) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        with(binding.rvFavoriteVacancy) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@FavoriteFragment.adapter
            itemAnimator = DefaultItemAnimator()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect{ state ->
                when{
                    state.error -> errorHandler()
                    state.loading -> loadingHandler()
                    else -> successDataHandler(state)
                }
            }
        }

        return binding.root
    }

    private fun loadingHandler(){
        binding.llFavoriteData.visibility = View.GONE
        binding.llError.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun errorHandler(){
        binding.llFavoriteData.visibility = View.GONE
        binding.llError.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE

        binding.imReload.setOnClickListener {
            viewModel.loadFavoriteVacancy()
        }
    }

    private fun successDataHandler(state: FavoriteState) {
        binding.llFavoriteData.visibility = View.VISIBLE
        binding.llError.visibility = View.GONE
        binding.progressBar.visibility = View.GONE

        val vacancyList = state.favoriteVacancyList.map { SearchItem.JobVacancyItem(it) }
        val vacancyCountText = DeclensionUtils.getVacancyDeclension(vacancyList.size)

        adapter.items = vacancyList
        binding.tvVacancyCount.text = vacancyCountText

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}