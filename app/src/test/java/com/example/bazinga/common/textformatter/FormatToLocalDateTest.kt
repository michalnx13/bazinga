package com.example.bazinga.common.textformatter

import com.example.bazinga.common.formatToLocalDate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

class FormatToLocalDateTest {

    private val date1 = "1.01.2023T12:51:23"
    private val date2 = "2023-01-13T12:51"
    private val date3 = "2023T12:51:23"

    private val char = 'T'

    private val formattedDate1 = date1.substringBefore(char)
    private val formattedDate2 = date2.substringBefore(char)
    private val formattedDate3 = date3.substringBefore(char)

    @TestFactory
    fun `returns correctly formatted date`() = listOf(
        date1 to formattedDate1,
        date2 to formattedDate2,
        date3 to formattedDate3
    ).map { (randomDateFormat, expectedFormattedDate) ->
        DynamicTest.dynamicTest("returns $expectedFormattedDate from $randomDateFormat") {
            assertEquals(expectedFormattedDate, randomDateFormat.formatToLocalDate(char))
        }
    }
}