package com.damoniy.grayscalerpg.api

import com.damoniy.grayscalerpg.block.GrayBlocks
import com.damoniy.grayscalerpg.item.GrayItems
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.registries.IForgeRegistry

object GeneralRegister {

    fun registerBlocks(event: IForgeRegistry<Block>) {
        GrayBlocks.get().forEach { block -> event.register(block); block.createItemFromBlock() }
    }

    fun registerItems(event: IForgeRegistry<Item>) {
        GrayItems.get().forEach { item -> event.register(item) }
    }
}