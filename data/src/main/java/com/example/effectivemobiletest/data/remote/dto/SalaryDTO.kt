package com.example.effectivemobiletest.data.remote.dto

import com.example.effectivemoviletest.domain.model.SalaryModel

data class SalaryDTO(
    val full: String,
    val short: String?
)

fun SalaryDTO.toSalaryModel(): SalaryModel{
    return SalaryModel(
        full = full,
        short = short
    )
}