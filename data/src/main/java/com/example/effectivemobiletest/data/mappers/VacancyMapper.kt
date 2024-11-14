package com.example.effectivemobiletest.data.mappers

import com.example.effectivemobiletest.data.local.room.entity.VacancyEntity
import com.example.effectivemoviletest.domain.model.VacancyModel


fun VacancyModel.toVacancyEntity(): VacancyEntity{
    return VacancyEntity(
        id = id,
        lookingNumber = lookingNumber,
        isFavorite = isFavorite,
        title = title,
        address = address,
        company = company,
        experienceText = experience,
        publishedDate = publishedDate
    )
}

fun VacancyEntity.toVacancyModel(): VacancyModel{
    return VacancyModel(
        id = id,
        address = address,
        company = company,
        experience= experienceText,
        isFavorite = isFavorite,
        lookingNumber = lookingNumber,
        publishedDate = publishedDate,
        title = title
    )
}
