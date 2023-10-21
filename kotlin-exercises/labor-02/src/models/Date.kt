package models

import java.time.LocalDate

data class Date(val year: Int, val month: Int, val day: Int) : Comparable<Date> {
    constructor() : this(LocalDate.now().year, LocalDate.now().monthValue, LocalDate.now().dayOfMonth)

    override fun compareTo(other: Date): Int = when{
        year != other.year -> year-other.year
        month != other.month -> month-other.month
        else -> day-other.day
    }
}