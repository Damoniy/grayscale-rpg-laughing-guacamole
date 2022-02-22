package com.damoniy.grayscalerpg.block

import com.damoniy.grayscalerpg.GrayScaleRPG
import com.damoniy.grayscalerpg.item.BlockItemFactory
import com.damoniy.grayscalerpg.item.GrayBlockItem
import net.minecraft.block.Block
import net.minecraft.util.ResourceLocation

abstract class GrayBlock(
    blockType: BlockType,
    val textureName: String): Block(blockType.properties) {
    private val blockItemFactory = BlockItemFactory()

    val blockName: String get() = "block_$textureName"

    init{
        this.registryName = ResourceLocation(GrayScaleRPG.ID, "$blockName")
        GrayBlocks.register(getModdedBlock())
    }

    fun getModdedBlock(): GrayBlock {
        return this.block as GrayBlock
    }

    fun createItemFromBlock(): GrayBlockItem {
        return blockItemFactory.createItemFromBlock(this)
    }
}