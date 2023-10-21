package models

class ComparatorDates {

    companion object : Comparator<Date> {

        override fun compare(a: Date, b: Date): Int = when {
            a.year != b.year -> a.year - b.year
            a.month != b.month -> a.month - b.month
            else -> a.day - b.day
        }
    }
}