package models

class DictionaryProvider private constructor() {

    private val dictionaryInstances = mutableMapOf<DictionaryType, IDictionary>()

    companion object {
        private val instance = DictionaryProvider()

        fun getInstance(): DictionaryProvider {
            return instance
        }
    }

    fun createDictionary(type: DictionaryType): IDictionary {
        return dictionaryInstances.getOrPut(type) {
            when (type) {
                DictionaryType.ARRAY_LIST -> ListDictionary()
                DictionaryType.TREE_SET -> TreeSetDictionary()
                DictionaryType.HASH_SET -> HashSetDictionary()

            }
        }
    }
}