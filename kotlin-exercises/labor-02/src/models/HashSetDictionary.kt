package models

import java.io.File

class HashSetDictionary : IDictionary {
    private val wordSet: HashSet<String> = HashSet()

    init {
        val wordsFromFile = readWordsFromFile("labor-02/src/dictionary.txt")
        wordSet.addAll(wordsFromFile)
    }

    override fun add(word: String): Boolean {
        if (!wordSet.contains(word)) {
            wordSet.add(word)
            return true
        }
        return false
    }

    override fun find(word: String): Boolean {
        return wordSet.contains(word)
    }

    override fun size(): Int {
        return wordSet.size
    }

    private fun readWordsFromFile(fileName: String): List<String> {
        val words = mutableListOf<String>()
        try {
            val file = File(fileName)
            if (file.exists()) {
                file.forEachLine {
                    words.add(it)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return words
    }
}