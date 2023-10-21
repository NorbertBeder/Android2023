package models

class ItemController(private val itemService: ItemService) {
    fun quiz(numQuestions: Int) {
        val quizItems = itemService.selectRandomItems(numQuestions)
        var correctAnswers = 0

        for ((index, item) in quizItems.withIndex()) {
            println("${index+1}) ${item.question}")

            for ((optionIndex, option) in item.answers.withIndex()) {
                println("\t${optionIndex + 1}. $option")
            }

            print("Your answer: ")
            val userAnswer = readlnOrNull()?.toIntOrNull()

            if (userAnswer == item.correct) {
                println("Correct!\n")
                correctAnswers++
            } else {
                println("Incorrect. The correct answer is: ${item.correct}\n")
            }
        }

        println("Quiz Result: $correctAnswers/$numQuestions correct answers")
    }
}