package com.example.effectivemobiletest.data.remote.dto

import com.example.effectivemoviletest.domain.model.ExperienceModel

data class ExperienceDTO(
    val previewText: String,
    val text: String
)

fun ExperienceDTO.toExperienceModel(): ExperienceModel{
    return ExperienceModel(
        previewText = previewText,
        text = text
    )
}