package com.example.effectivemobiletest.domain.utils

import com.example.effectivemobiletest.presentation.utils.DeclensionUtils
import org.junit.Assert.*
import org.junit.Test

class DeclensionGetVacancyDeclension{

    @Test
    fun `returns correct declension for 1 vacancy`() {
        assertEquals("1 вакансия", DeclensionUtils.getVacancyDeclension(1))
    }

    @Test
    fun `returns correct declension for 2 vacancies`() {
        assertEquals("2 вакансии", DeclensionUtils.getVacancyDeclension(2))
    }

    @Test
    fun `returns correct declension for 4 vacancies`() {
        assertEquals("4 вакансии", DeclensionUtils.getVacancyDeclension(4))
    }

    @Test
    fun `returns correct declension for 5 vacancies`() {
        assertEquals("5 вакансий", DeclensionUtils.getVacancyDeclension(5))
    }

    @Test
    fun `returns correct declension for 11 vacancies`() {
        assertEquals("11 вакансий", DeclensionUtils.getVacancyDeclension(11))
    }

    @Test
    fun `returns correct declension for 21 vacancy`() {
        assertEquals("21 вакансия", DeclensionUtils.getVacancyDeclension(21))
    }

    @Test
    fun `returns correct declension for 22 vacancies`() {
        assertEquals("22 вакансии", DeclensionUtils.getVacancyDeclension(22))
    }

    @Test
    fun `returns correct declension for 25 vacancies`() {
        assertEquals("25 вакансий", DeclensionUtils.getVacancyDeclension(25))
    }

    @Test
    fun `returns correct declension for 100 vacancies`() {
        assertEquals("100 вакансий", DeclensionUtils.getVacancyDeclension(100))
    }

    @Test
    fun `returns correct declension for 111 vacancies`() {
        assertEquals("111 вакансий", DeclensionUtils.getVacancyDeclension(111))
    }

    @Test
    fun `returns correct declension for 102 vacancies`() {
        assertEquals("102 вакансии", DeclensionUtils.getVacancyDeclension(102))
    }

    @Test
    fun `returns correct declension for 104 vacancies`() {
        assertEquals("104 вакансии", DeclensionUtils.getVacancyDeclension(104))
    }
}