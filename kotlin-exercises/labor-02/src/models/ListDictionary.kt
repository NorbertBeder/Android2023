package models

import java.io.File

class ListDictionary : IDictionary {
    private val wordList: MutableList<String> = mutableListOf<String>()

    init {
        val wordsFromFile = readWordsFromFile("labor-02/src/dictionary.txt")
        wordList.addAll(wordsFromFile)
    }

    override fun add(word: String): Boolean {
        if (!wordList.contains(word)) {
            wordList.add(word)
            return true
        }
        return false
    }

    override fun find(word: String): Boolean {
        return wordList.contains(word)
    }

    override fun size(): Int {
        return wordList.size
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