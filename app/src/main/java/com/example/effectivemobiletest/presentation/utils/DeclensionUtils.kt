package com.example.effectivemobiletest.presentation.utils

object DeclensionUtils {

    fun getPersonDeclension(number: Int?): String? {
        if (number == null) return null

        var str = "Сейчас просматривает $number "
        str += when {
            number % 10 == 1 && number % 100 != 11 -> "человек"
            number % 10 in 2..4 && (number % 100 !in 12..14) -> "человека"
            else -> "человек"
        }

        return str
    }


    fun getVacancyDeclension(count: Int): String {
        val lastDigit = count % 10
        val lastTwoDigits = count % 100

        val str = "$count "

        return str + when {
            lastTwoDigits in 11..14 -> "вакансий"
            lastDigit == 1 -> "вакансия"
            lastDigit in 2..4 -> "вакансии"
            else -> "вакансий"
        }
    }
}