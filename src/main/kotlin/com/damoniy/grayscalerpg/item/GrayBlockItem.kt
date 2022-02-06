package com.damoniy.grayscalerpg.item

import net.minecraft.block.Block
import net.minecraft.item.BlockItem

class GrayBlockItem(block: Block, blockItemName: String): BlockItem(block, ItemType.AMBIENT.properties) {
    init{
        this.setRegistryName(blockItemName)
        GrayItems.register(this)
    }
}