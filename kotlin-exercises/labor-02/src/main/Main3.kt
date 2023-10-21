package main

import models.ComparatorDates
import java.time.LocalDate
import kotlin.random.Random
import models.Date

fun main() {
    val currentDate = Date()
    println("Current Date: $currentDate")
    println("Is current date a leap year: ${currentDate.isLeapYear()}")
    val wrongDate = Date(2021, 2, 29)

    println("Is $currentDate a correct date: ${currentDate.isValidDate()}")
    println("Is $wrongDate a correct date: ${wrongDate.isValidDate()}\n")

    val validDates = mutableListOf<Date>()
    val random = Random(System.currentTimeMillis())
    var validDateCount = 0

    while (validDateCount < 10) {
        val year = random.nextInt(0, LocalDate.now().year + 1)
        val month = random.nextInt(0, 14)
        val day = random.nextInt(0, 33)
        val randomDate = Date(year, month, day)

        if (randomDate.isValidDate()) {
            validDates.add(randomDate)
            validDateCount++
        } else {
            println("Invalid date: $randomDate")
        }
    }

    println("\n\nValid Dates:")
    validDates.forEach { println(it) }


    println("\n\nSorted Dates using Comparable (natural ordering):")
    val sortedDatesNat = validDates.sorted()
    sortedDatesNat.forEach { println(it) }

    println("\n\nReverse Dates:")
    sortedDatesNat.reversed().forEach { println(it) }

    println("\n\nSorted Dates using Comparator (custom ordering):")
    val sortedDatesCus = validDates.sortedWith(ComparatorDates)
    sortedDatesCus.forEach { println(it) }

}

fun Date.isLeapYear(): Boolean {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}

fun Date.isValidDate(): Boolean {
    val currentYear = LocalDate.now().year

    if (year !in 1..currentYear) return false

    if (month !in 1..12) return false

    return when (month) {
        2 -> {
            if (isLeapYear()) {
                day in 1..29
            } else {
                day in 1..28
            }
        }

        4, 6, 9, 11 -> day in 1..30
        else -> day in 1..31
    }
}