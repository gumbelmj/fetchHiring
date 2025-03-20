package com.fetch.hiring.model

class ItemMapper {

    private val comparator = Comparator<Item> { o1, o2 -> (o1.name ?: "").compareTo(o2.name ?: "") }

    fun map(items: List<Item>): Map<Int, List<Item>> {
        val result = HashMap<Int, MutableList<Item>>()

        for (item in items) {
            if (!item.name.isNullOrBlank()) {
                var list = result[item.listId] ?: ArrayList<Item>()
                list.add(item)
                result.put(item.listId, list)
            }
        }

        for (value in result.values) {
            value.sortWith(comparator)
        }

        return result
    }
}