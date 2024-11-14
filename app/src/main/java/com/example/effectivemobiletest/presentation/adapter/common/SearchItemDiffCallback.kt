package com.example.effectivemobiletest.presentation.adapter.common

import androidx.recyclerview.widget.DiffUtil

object SearchItemDiffCallback: DiffUtil.ItemCallback<SearchItem>() {

    override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
        return when{
            oldItem is SearchItem.JobVacancyItem && newItem is SearchItem.JobVacancyItem ->
                oldItem.vacancy.id == newItem.vacancy.id
            oldItem is SearchItem.OfferItemList && newItem is SearchItem.OfferItemList ->
                oldItem == newItem
            oldItem is SearchItem.ButtonItem && newItem is SearchItem.ButtonItem -> true
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean {
        if (oldItem is SearchItem.JobVacancyItem && newItem is SearchItem.JobVacancyItem)
            return when{
                oldItem.vacancy.isFavorite != newItem.vacancy.isFavorite -> false
                oldItem.vacancy.publishedDate != newItem.vacancy.publishedDate -> false
                oldItem.vacancy.title != newItem.vacancy.title -> false
                oldItem.vacancy.address != newItem.vacancy.address -> false
                oldItem.vacancy.lookingNumber != newItem.vacancy.lookingNumber -> false
                oldItem.vacancy.company != newItem.vacancy.company -> false
                oldItem.vacancy.experience != newItem.vacancy.experience -> false
                oldItem.vacancy.id != newItem.vacancy.id -> false
                else -> true
            }

        if(oldItem is SearchItem.ButtonItem && newItem is SearchItem.ButtonItem){
            return when{
                oldItem.text == newItem.text -> true
                else -> false
            }
        }
        if (oldItem is SearchItem.OfferItemList && newItem is SearchItem.OfferItemList)
            return true

        return false
    }


}
