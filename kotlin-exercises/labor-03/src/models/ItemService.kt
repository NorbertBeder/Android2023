package models

class ItemService(private val itemRepository: ItemRepository) {
    fun selectRandomItems(number: Int): List<Item> {
        val selectedItems = mutableListOf<Item>()
        while (selectedItems.size != number && selectedItems.size != itemRepository.size()) {
            val randomItem = itemRepository.randomItem()
            if (!selectedItems.contains(randomItem)) {
                selectedItems.add(randomItem)
            }
        }
        return selectedItems
    }
}