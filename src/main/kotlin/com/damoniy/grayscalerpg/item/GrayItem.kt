package com.damoniy.grayscalerpg.item

import net.minecraft.item.Item

abstract class GrayItem(type: ItemType, itemName: String): Item(type.properties) {
    init{
        this.setRegistryName(itemName)
    }
}