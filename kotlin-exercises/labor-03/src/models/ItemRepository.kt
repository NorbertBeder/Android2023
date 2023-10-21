package models

class ItemRepository {
    private val items = mutableListOf(
        Item("What is Kotlin's primary use case?", listOf("Web development", "Mobile app development", "Game development", "Data analysis"), 2),
        Item("In Kotlin, which keyword is used to define a read-only property?", listOf("var", "val", "const", "readonly"), 2),
        Item("What is the Kotlin when expression used for?", listOf("Defining functions", "Looping through arrays", "Making decisions based on a value", "Importing libraries"), 3),
        Item("In Kotlin, which keyword is used to declare an interface?", listOf("interface", "abstract", "class", "implement"), 1),
        Item("What is the Kotlin standard library function for filtering a list?", listOf("filter", "select", "choose", "pick"), 1),
        Item("In Kotlin, what is the safe call operator?", listOf("?", "!!", "??", "?: "), 1),
        Item("Which Kotlin feature is used to avoid nullable references and null pointer exceptions?", listOf("try-catch", "lateinit", "safe call operator (?.)", "non-null assertion operator (!!)"), 3),
        Item("In Kotlin, what is an \"init\" block used for in a class?", listOf("Initializing properties", "Defining member functions", "Creating constructors", "Importing external libraries"), 1),
        Item("Which of the following is NOT a visibility modifier in Kotlin?", listOf("public", "private", "protected", "restricted"), 4)

    )
    fun randomItem(): Item {
        return items.random()
    }

    fun save(item: Item) {
        items.add(item)
    }

    fun size(): Int {
        return items.size
    }
}