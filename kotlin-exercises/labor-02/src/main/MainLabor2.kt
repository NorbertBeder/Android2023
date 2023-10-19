package main

fun main(){
    val name = "Axel Jones"
    val monogram = name.printMonogram()
    println(monogram)

    val listWithoutSeperator = listOf("apple", "pear", "melon")
    val separator = "#"
    val newString = listWithoutSeperator.joinWithSeparator(separator)
    println(newString)

    val listForLongest = listOf("apple", "pear", "strawberry", "melon")
    val longest = listForLongest.findLongest()
    println("Longest: $longest")
}
fun String.printMonogram(): String {
    return split(" ")
        .map { it.first() }
        .joinToString("")
}

fun List<String>.joinWithSeparator(separator: String): String = joinToString(separator)

fun List<String>.findLongest(): String? = maxByOrNull { it.length }
