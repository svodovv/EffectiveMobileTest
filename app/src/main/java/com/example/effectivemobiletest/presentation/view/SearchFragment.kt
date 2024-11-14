package com.example.effectivemobiletest.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.FragmentSearchBinding
import com.example.effectivemobiletest.presentation.adapter.search.ParentSearchVacancyAdapter
import com.example.effectivemobiletest.presentation.adapter.common.SearchItem
import com.example.effectivemobiletest.presentation.state.SearchState
import com.example.effectivemobiletest.presentation.viewmodel.SearchViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<SearchViewModel>()


    private val adapter = ParentSearchVacancyAdapter(
        btnOnClick = { viewModel.setFullData() },
        heartOnClick = { viewModel.heartClick(it) }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        with(binding.rvSearch) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@SearchFragment.adapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                when {
                    state.error -> errorHandler()
                    state.loading -> loadingDataHandler()
                    else -> successData(state)
                }
            }
        }

        return binding.root

    }

    private fun errorHandler() {
        with(binding) {
            llSearch.visibility = View.GONE
            progressBar.visibility = View.GONE
            llError.visibility = View.VISIBLE

            imReload.setOnClickListener { viewModel.loadData() }

        }
    }

    private fun loadingDataHandler() {
        with(binding) {
            llSearch.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            llError.visibility = View.GONE

        }
    }

    private fun successData(state: SearchState) {
        val vacancyItems = state.vacancyList.map { SearchItem.JobVacancyItem(it) }
        val offerItems = SearchItem.OfferItemList(state.offerList)
        val vacancyCountDeclension = state.vacancyCountDeclension


        if (vacancyCountDeclension != null) {
            val buttonText = SearchItem.ButtonItem("Еще $vacancyCountDeclension")
            adapter.items = if (state.isLimitedData) listOf(offerItems) + vacancyItems + buttonText
            else
                listOf(offerItems) + vacancyItems
        }

        with(binding) {
            llSearch.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            llError.visibility = View.GONE

            tilSearch.startIconDrawable = ContextCompat.getDrawable(
                requireContext(), if (state.isLimitedData) R.drawable.ic_menu_item_search_default
                else R.drawable.baseline_arrow_back
            )
            tilSearch.hint = ContextCompat.getString(
                requireContext(), if (state.isLimitedData) R.string.search_key_words1
                else R.string.search_key_words2
            )

            if (!state.isLimitedData)
                tilSearch.setStartIconOnClickListener { viewModel.setLimitedData() }

            if(!state.isLimitedData){
                llByCorrespondence.visibility = View.VISIBLE
                rvSearch.setPadding(0, 25,0,0)
                tvByCorrespondence.text = vacancyCountDeclension
            }else
                llByCorrespondence.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}