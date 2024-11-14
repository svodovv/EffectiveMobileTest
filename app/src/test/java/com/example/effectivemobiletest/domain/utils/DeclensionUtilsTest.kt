package com.example.effectivemobiletest.domain.utils

import com.example.effectivemobiletest.presentation.utils.DeclensionUtils
import org.junit.Test
import org.junit.Assert.*

class DeclensionUtilsGetPersonDeclensionTest {

    private val plural = "человека"
    private val singular = "человек"

    @Test
    fun `set 1 return singular`() {
        val declensionUtils = DeclensionUtils.getPersonDeclension(1)?.split(" ")?.last()

        assertEquals(declensionUtils, singular)
    }

    @Test
    fun `set 2 return plural`() {
        val declensionUtils = DeclensionUtils.getPersonDeclension(2)?.split(" ")?.last()

        assertEquals(declensionUtils, plural)
    }

    @Test
    fun `set 22 return plural`() {
        val declensionUtils = DeclensionUtils.getPersonDeclension(22)?.split(" ")?.last()

        assertEquals(declensionUtils, plural)
    }

    @Test
    fun `set 27 return singular`() {
        val declensionUtils = DeclensionUtils.getPersonDeclension(27)?.split(" ")?.last()

        assertEquals(declensionUtils, singular)
    }

    @Test
    fun `set 107 return singular`() {
        val declensionUtils = DeclensionUtils.getPersonDeclension(107)?.split(" ")?.last()

        assertEquals(declensionUtils, singular)
    }


    @Test
    fun `set null  return null`() {
        val declensionUtils = DeclensionUtils.getPersonDeclension(null)?.split(" ")?.last()

        assertEquals(declensionUtils, null)
    }

    @Test
    fun `set listSingular return true`() {

        val numbersSingulars = listOf(1, 21, 31, 41, 51, 61, 71, 81, 91, 101, 111, 121, 131, 141, 151, 161, 171, 181, 191, 201, 211, 221, 231, 241)

        numbersSingulars.forEach {
            val declensionUtils = DeclensionUtils.getPersonDeclension(it    )?.split(" ")?.last()

            assertEquals(declensionUtils, singular)
        }

    }

    @Test
    fun `set listPlural return true`() {

        val numbersPlural = listOf(
        2, 3, 4, 22, 23, 24, 32, 33, 34, 42, 43, 44, 52, 53, 54, 62, 63, 64, 72, 73, 74, 82, 83, 84, 92, 93, 94,
        102, 103, 104,  122, 123, 124, 132, 133, 134, 142, 143, 144, 152, 153, 154, 162, 163, 164,
        172, 173, 174, 182, 183, 184, 192, 193, 194, 202, 203, 204
        )

        numbersPlural.forEach {
            val declensionUtils = DeclensionUtils.getPersonDeclension(it)?.split(" ")?.last()

            assertEquals("Test failed for number $it. Expected: $plural, but got: $declensionUtils", declensionUtils, plural, )
        }

    }

}