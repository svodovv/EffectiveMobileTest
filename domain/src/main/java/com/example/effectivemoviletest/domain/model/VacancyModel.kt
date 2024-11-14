package com.example.effectivemoviletest.domain.model

data class VacancyModel(
    val id: String,
    val address: String,
    val company: String,
    val experience: String,
    val isFavorite: Boolean,
    val lookingNumber: Int?,
    val publishedDate: String,
    val title: String
)