package com.damoniy.grayscalerpg.item

import com.damoniy.grayscalerpg.block.GrayBlock

class BlockItemFactory() {
    fun createItemFromBlock(block: GrayBlock): GrayBlockItem {
        val blockItemName = "item_${block.blockName}"
        return GrayBlockItem(block, blockItemName)
    }
}