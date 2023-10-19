package main

import models.*
import java.util.*

fun main(){
    val provider = DictionaryProvider.getInstance()

    println("Choose a dictionary type:")
    println("1. ArrayList")
    println("2. TreeSet")
    println("3. HashSet")

    print("Enter your choice (1/2/3): ")
    val scanner = Scanner(System.`in`)
    val choice = scanner.nextInt()


    val dict = when (choice) {
        1 -> provider.createDictionary(DictionaryType.ARRAY_LIST)
        2 -> provider.createDictionary(DictionaryType.TREE_SET)
        3 -> provider.createDictionary(DictionaryType.HASH_SET)
        else -> throw IllegalArgumentException("Invalid dictionary type")
    }


    println("Number of words: ${dict.size()}")
    var word: String?
    while(true){
        print("What to find? ")
        word = readLine()
        if( word.equals("quit")){
            break
        }
        println("Result: ${word?.let { dict.find(it) }}")
    }

}


