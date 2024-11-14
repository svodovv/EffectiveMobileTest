package com.example.effectivemobiletest.data.remote.dto

import com.example.effectivemobiletest.data.remote.dto.JobDTO.Companion.toVacancyModel
import com.example.effectivemoviletest.domain.model.OfferModel
import com.example.effectivemoviletest.domain.model.VacancyModel

data class JobDTO(
    val offers: List<OfferDTO>,
    val vacancies: List<VacancyDTO>
){
    companion object{
        fun JobDTO.toOfferModel(): List<OfferModel>{
            return offers.map {
                OfferModel(
                    buttonText = it.button?.text,
                    id = it.id ,
                    link = it.link,
                    title = it.title
                )
            }
        }

        fun JobDTO.toVacancyModel(): List<VacancyModel>{
            return vacancies.map {
                VacancyModel(
                    address = it.address.town,
                    company = it.company,
                    experience = it.experience.previewText,
                    id = it.id,
                    isFavorite = it.isFavorite,
                    lookingNumber = it.lookingNumber,
                    publishedDate = it.publishedDate,
                    title = it.title
                )
            }
        }
    }
}