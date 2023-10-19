package main

import kotlin.random.Random

fun main(args: Array<String>) {

    //addTwoVal(2, 3)
    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    println("1.feladat:\n")
    addTwoVal(1, 2)

    println("\n2.feladat:\n")
    daysOfTheWeek(days)

    println("\n3.feladat:\n")
    primeCheck(1)
    for (i in 1..25) {
        print("$i: ")
        primeCheck(i)
    }

    println("\n4.feladat:\n")
    val encodedMsg = (messageCoding("Hello", ::encodeString))
    val decodedMsg = (messageCoding(encodedMsg, ::decodeString))
    println(encodedMsg)
    println(decodedMsg)

    println("\n5.feladat:\n")
    val numList: List<Int> = listOf(1, 2, 3, 4, 5, 6)
    println(evenNumbers(numList))

    println("\n6.feladat:\n")
    println(doubleElems(numList))

    println(capitalizedWords(days))

    println(firstChar(days))

    val daysLength = (lengthOfDays(days))
    println(daysLength)

    println(averageOfDays(daysLength))

    println("\n7.feladat:\n")
    val mutableDays = days.toMutableList()

    removeN(mutableDays)

    println("\n8.feladat:\n")
    val randomArray = IntArray(10) { Random.nextInt(0, 101) }

    arrays(randomArray)

}

// 1.feladat
fun addTwoVal(x: Int, y: Int): Unit {
    println("$x + $y = ${x + y}\n")
}

// 2.feladat
fun daysOfTheWeek(days: List<String>): Unit {
    for (d in days) {
        println(d)
    }
    println()
    val startWithT = days.filter { it.startsWith("T") }
    println(startWithT)

    val containsE = days.filter { it.contains("e") }
    println(containsE)

    val lengthIsSix = days.filter { x -> x.length == 6 }
    println(lengthIsSix)
}

// 3.feladat
fun primeCheck(n: Int): Unit {
    for (i in 2..n / 2) {
        if (n % i == 0) {
            println("The $n number is not prime")
            return
        }
    }
    println("The $n number is prime")
}

// 4.feladat
fun encodeString(inputString: String): String {
    return inputString.map { it.code.toString(16) }.joinToString("")
}

fun decodeString(input: String): String {
    val hexPairs = input.chunked(2)
    return hexPairs.map { it.toInt(16).toChar() }.joinToString("")
}

fun messageCoding(msg: String, func: (String) -> String): String {
    return func(msg)
}

// 5.feladat
fun evenNumbers(numList: List<Int>): List<Int> = numList.filter { it % 2 == 0 }

// 6.feladat
fun doubleElems(numList: List<Int>): List<Int> = numList.map { it * 2 }

fun capitalizedWords(days: List<String>): List<String> = days.map { it.uppercase() }

fun firstChar(days: List<String>): List<Char> = days.map { it.first().lowercaseChar() }

fun lengthOfDays(days: List<String>): List<Int> = days.map { it.length }

fun averageOfDays(daysLength: List<Int>): Double = daysLength.average()

// 7.feladat
fun removeN(mutableDays: MutableList<String>): Unit {
    mutableDays.removeIf { it.contains('n', ignoreCase = true) }

    println(mutableDays)

    for ((index, day) in mutableDays.withIndex()) {
        println("Item at $index is $day")
    }

    mutableDays.sort()

    println(mutableDays)
}

// 8.feladat
fun arrays(randomArray: IntArray) {
    randomArray.forEach { println(it) }

    println("Sorted:\n")

    val sortedArray = randomArray.sorted()
    sortedArray.forEach { println(it) }

    if (randomArray.any { it % 2 == 0 }) {
        println("The array contains at least one even number.")
    } else {
        println("The array does not contains at least one even number.")
    }

    if(randomArray.all { it % 2 == 0 }){
        println("All the numbers are even.")
    }else{
        println("Not all of the numbers are even.")
    }

    val avg = randomArray.average()

    println("The average: $avg")
}