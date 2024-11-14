package com.example.effectivemobiletest.presentation.utils

sealed class OfferId(val value: String) {
    data object NearVacancies : OfferId("near_vacancies")
    data object LevelUpResume : OfferId("level_up_resume")
    data object TemporaryJob : OfferId("temporary_job")

}