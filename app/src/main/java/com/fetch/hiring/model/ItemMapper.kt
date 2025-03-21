package com.fetch.hiring.model

class ItemMapper {

    fun map(items: List<Item>): Map<String, List<String>> {
        val result = HashMap<String, MutableList<String>>()

        for (item in items) {
            if (!item.name.isNullOrBlank()) {
                val key = item.listId.toString()
                var list = result[key] ?: ArrayList<String>()
                list.add(item.name)
                result.put(key, list)
            }
        }

        for (value in result.values) {
            value.sort()
        }

        return result
    }
}