package com.damoniy.grayscalerpg.item

import net.minecraft.item.Item

object GrayItems {
    private val items = ArrayList<Item>()

    fun register(item: Item) {
        items.add(item)
    }

    fun get(): List<Item> {
        return items
    }
}