package com.damoniy.grayscalerpg.block

import net.minecraft.block.AbstractBlock
import net.minecraft.block.Blocks

enum class BlockType(val properties: AbstractBlock.Properties) {
    DIRT(AbstractBlock.Properties.copy(Blocks.DIRT)),
    GRASS(AbstractBlock.Properties.copy(Blocks.GRASS)),
    WOOD(AbstractBlock.Properties.copy(Blocks.SPRUCE_WOOD)),
    LIQUID(AbstractBlock.Properties.copy(Blocks.WATER)),
    LAMP(AbstractBlock.Properties.copy(Blocks.GLOWSTONE)),
    PLANKS(AbstractBlock.Properties.copy(Blocks.SPRUCE_PLANKS))
}