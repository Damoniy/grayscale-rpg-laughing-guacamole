package com.damoniy.grayscalerpg.item

import net.minecraft.item.Item.Properties
import net.minecraft.item.ItemGroup

enum class ItemType(val properties: Properties) {
    MISCELLANEOUS(Properties().tab(ItemGroup.TAB_MISC)),
    AMBIENT(Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS))
}