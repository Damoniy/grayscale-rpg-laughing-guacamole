package com.damoniy.grayscalerpg.item

import com.damoniy.grayscalerpg.block.GrayBlock

class BlockItemFactory() {
    fun createItemFromBlock(block: GrayBlock): GrayBlockItem {
        val blockItemName = block.blockName.replace("block", "item")
        return GrayBlockItem(block, blockItemName)
    }
}