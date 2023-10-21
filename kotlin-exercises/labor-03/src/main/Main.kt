package main
import models.ItemController
import models.ItemRepository
import models.ItemService

fun main() {
    val itemRepository = ItemRepository()
    val itemService = ItemService(itemRepository)
    val itemController = ItemController(itemService)

    println("Kotlin Quiz")
    print("Enter the number of questions: ")
    val numQuestions = 6

    itemController.quiz(numQuestions)
}