package com.example.effectivemobiletest.presentation.adapter.common

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.databinding.RecycleSearchOfferBinding
import com.example.effectivemobiletest.databinding.RecyclerSearchButtonItemBinding
import com.example.effectivemobiletest.databinding.RecyclerSearchOfferItemBinding
import com.example.effectivemobiletest.databinding.RecyclerSearchVacancyItemBinding
import com.example.effectivemobiletest.presentation.adapter.search.ChildSearchOfferAdapter
import com.example.effectivemobiletest.presentation.utils.DeclensionUtils
import com.example.effectivemobiletest.presentation.utils.OfferId
import com.example.effectivemoviletest.domain.model.OfferModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object AdapterDelegate {
    fun parentSearchVacancyAdapterDelegate(heartOnClick: (String) -> Unit) =
        adapterDelegateViewBinding<SearchItem.JobVacancyItem, SearchItem, RecyclerSearchVacancyItemBinding>(
            { inflate, container ->
                RecyclerSearchVacancyItemBinding.inflate(
                    inflate, container, false
                )
            }) {
            binding.ivHeart.setOnClickListener { heartOnClick(item.vacancy.id) }

            bind {
                with(binding) {

                    if (item.vacancy.lookingNumber == null)
                        tvLookingNumber.visibility = View.GONE
                    else {
                        tvLookingNumber.visibility = View.VISIBLE
                        val lookingText = DeclensionUtils.getPersonDeclension(item.vacancy.lookingNumber)
                        tvLookingNumber.text = lookingText
                    }


                    ivHeart.setImageResource(
                        if (!item.vacancy.isFavorite) R.drawable.ic_search_item_vacancy_heart_empty
                        else R.drawable.ic_search_item_vacancy_heart_fill
                    )

                    tvTitle.text = item.vacancy.title
                    tvAddress.text = item.vacancy.address
                    tvCompanyName.text = item.vacancy.company
                    tvExperience.text = item.vacancy.experience
                    tvPublishedDate.text = item.vacancy.publishedDate


                }
            }
        }

    fun childSearchOfferAdapterDelegate() =
        adapterDelegateViewBinding<SearchItem.OfferItemList, SearchItem, RecycleSearchOfferBinding>(
            { layoutInflater, parent ->
                RecycleSearchOfferBinding.inflate(
                    layoutInflater, parent, false
                )
            }) {

            val childSearchOfferAdapter = ChildSearchOfferAdapter()

            bind {
                childSearchOfferAdapter.items = item.offerList
                binding.rvOffer.adapter = childSearchOfferAdapter
                binding.rvOffer.layoutManager =
                    LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)

            }
        }

    fun childSearchOfferItemAdapterDelegate() =
        adapterDelegateViewBinding<OfferModel, OfferModel, RecyclerSearchOfferItemBinding>({ inflate, container ->
            RecyclerSearchOfferItemBinding.inflate(
                inflate, container, false
            )
        }) {

            bind {
                with(binding) {
                    setOfferTitle(
                        item.id
                    ) {
                        if (it == null) return@setOfferTitle
                        val (color, image) = it

                        ivOfferIcon.setBackgroundResource(color)
                        ivOfferIcon.setImageResource(image)
                    }

                    if (item.buttonText != null) {
                        tvSelectedText.visibility = View.VISIBLE
                        tvSelectedText.text = item.buttonText
                        tvSelectedText.maxLines = 3
                    }else{
                        tvSelectedText.visibility = View.GONE
                        tvSelectedText.maxLines = 2

                    }

                    tvRecommendationText.text = item.title


                }
            }
        }

    fun vacancyButtonAdapterDelegate(onClick: () -> Unit) =
        adapterDelegateViewBinding<SearchItem.ButtonItem, SearchItem, RecyclerSearchButtonItemBinding>(
            { inflate, container ->
                RecyclerSearchButtonItemBinding.inflate(
                    inflate,
                    container,
                    false
                )
            }) {


            bind {

                binding.btnSearchItem.setOnClickListener {
                    onClick()
                }

                binding.btnSearchItem.text = item.text
            }
        }


    private fun setOfferTitle(
        itemId: String?, colorIntImageIntPair: (Pair<Int, Int>?) -> Unit
    ) {
        colorIntImageIntPair(
            when (itemId) {
                OfferId.NearVacancies.value -> Pair(
                    R.drawable.blue_recommendation_icon_background,
                    R.drawable.ic_search_item_offer_location
                )

                OfferId.TemporaryJob.value -> Pair(
                    R.drawable.green_recommendation_icon_background,
                    R.drawable.ic_search_item_offer_list
                )

                OfferId.LevelUpResume.value -> Pair(
                    R.drawable.green_recommendation_icon_background,
                    R.drawable.ic_search_item_offer_star
                )

                else -> null
            }
        )

    }
}

